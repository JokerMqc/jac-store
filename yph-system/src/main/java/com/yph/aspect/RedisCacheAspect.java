package com.yph.aspect;

import com.github.pagehelper.Page;
import com.yph.common.annotation.RedisCache;
import com.yph.common.annotation.RedisEvict;
import com.yph.common.constant.RedisKey;
import com.yph.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * redis 缓存切面
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/12
 **/
@Slf4j
@Aspect
@Component
public class RedisCacheAspect {

    @Autowired
    protected RedisTemplate redisTemplate;

    @Value("${redis.redisStatus}")
    private String redisStatus;

    /**
     * 方法调用前，先查询缓存。如果存在缓存，则返回缓存数据，阻止方法调用;
     * 如果没有缓存，则调用业务方法，然后将结果放到缓存中
     *
     * @param jp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.yph.service.*.impl.*.find*(..))" +
            "|| execution(* com.yph.service.*.impl.*.select*(..))" +
            "|| execution(* com.yph.service.*.impl.*.get*(..))" +
            "|| execution(* com.yph.common.base.BaseServiceImpl.find*(..))" +
            "|| execution(* com.yph.common.base.BaseServiceImpl.get*(..))" +
            "|| execution(* com.yph.common.base.BaseServiceImpl.select*(..))")
    public Object cache(ProceedingJoinPoint jp) throws Throwable {

        Object result = null;
        // 得到类名、方法名和参数
        String clazzName = jp.getTarget().getClass().getSimpleName();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();

        // 判断redis 是否开启
        if (redisStatus != null && redisStatus.equals("on")) {
            log.debug("[ {}  redis 从缓存中获取数据 --------------------> START   ]", methodName);
            // 根据类名，方法名和参数生成key
            String key = genKey(clazzName, methodName, args);
            log.debug(" [ 缓存 : key  --------------> ] key:{}", key);
            // 得到被代理的方法

            Method me = ((MethodSignature) jp.getSignature()).getMethod();
            // 得到被代理的方法上的注解
            Class modelType = null;
            String value = null;

            RedisCache annotation = me.getAnnotation(RedisCache.class);
            if(annotation==null){
                // 方法上没有的 则从类上面获取注解类型.
                Annotation[] annotations = jp.getTarget().getClass().getAnnotations();
                annotation =getRedisCacheForClass(annotations);
            }

            if (annotation != null) {
                modelType = annotation.type();
                // 检查redis中是否有缓存
                value = (String) redisTemplate.opsForHash().get(clazzName, key);

                // result是方法的最终返回结果
                if (null == value) {
                    log.debug("[   redis 未找到该key的值  --------------> key : {} ]", key);
                    // 调用数据库查询方法
                    result = jp.proceed(args);
                    // 序列化查询结果
                    String json = serialize(result);
                    // 序列化结果放入缓存
                    redisTemplate.opsForHash().put(clazzName, key, json);
                } else {
                    // 缓存命中
                    log.debug("[   redis 找到该key的值  --------------> key : {} ]", key);
                    // 得到被代理方法的返回值类型
                    Class returnType = ((MethodSignature) jp.getSignature()).getReturnType();
                    // 反序列化从缓存中拿到的json
                    result = deserialize(value, returnType, modelType);
                    log.debug(" [ 从redis 中获取json数据再序列化成对象 ----------->  ] {}", result);
                }
            } else {
                result = jp.proceed(args);
            }
        } else {
            // 没有开启
            result = jp.proceed(args);
        }
        return result;
    }



    /**
     * 在方法调用前清除缓存，然后调用业务方法
     *
     * @param jp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.yph.service.*.impl.*.del*(..))" +
            "|| execution(* com.yph.service.*.impl.*.save*(..))" +
            "|| execution(* com.yph.service.*.impl.*.batch*(..))" +
            "|| execution(* com.yph.service.*.impl.*.update*(..))" +
            "|| execution(* com.yph.service.*.impl.*.insert*(..))+" +
            "|| execution(* com.yph.common.base.BaseServiceImpl.save*(..))" +
            "|| execution(* com.yph.common.base.BaseServiceImpl.update*(..))" +
            "|| execution(* com.yph.common.base.BaseServiceImpl.del*(..))")
    public Object evictCache(ProceedingJoinPoint jp) throws Throwable {
        if (redisStatus != null && redisStatus.equals("on")) {
            // 得到被代理的方法
            Method me = ((MethodSignature) jp.getSignature()).getMethod();

            // 得到被代理的方法上的注解
            RedisEvict annotation = me.getAnnotation(RedisEvict.class);
            if(annotation==null){
                // 方法上没有的 则从类上面获取注解类型.
                Annotation[] annotations = jp.getTarget().getClass().getAnnotations();
                annotation =getRedisEvictForClass(annotations);
            }
            if (annotation != null) {
                // 得到类名、方法名和参数
                String clazzName = jp.getTarget().getClass().getSimpleName();
                Class modelType = annotation.type();
                String[] evicts = annotation.evicts();
                if (log.isDebugEnabled()) {
                    log.debug("[ 清空缓存 ------------------------> ]:{}", clazzName);
                }
                // 清除对应缓存
                redisTemplate.delete(clazzName);
                if (evicts.length > 0) {
                    for (String evictClassName : evicts) {
                        redisTemplate.delete(evictClassName);
                    }
                }
            }
        }
        return jp.proceed(jp.getArgs());
    }


    /**
     * 根据类名、方法名和参数生成key
     *
     * @param clazzName
     * @param methodName
     * @param args       方法参数
     * @return
     */
    protected String genKey(String clazzName, String methodName, Object[] args) {
        StringBuilder sb = new StringBuilder(clazzName);
        sb.append(RedisKey.COLON);
        sb.append(methodName);
        sb.append(RedisKey.COLON);
        for (Object obj : args) {
            if (obj != null) {
                sb.append(obj.toString());
                sb.append(RedisKey.COLON);
            }
        }
        return sb.toString();
    }


    /**
     *  获取缓存注解
     * @param annotations
     * @return
     */
    private RedisEvict getRedisEvictForClass(Annotation[] annotations) {
        for(Annotation annotation :annotations){
            if(annotation instanceof RedisEvict){
                return (RedisEvict) annotation;
            }
        }
        return null;
    }

    /**
     * 获取缓存的注解
     *
     * @param annotations
     */
    private RedisCache getRedisCacheForClass( Annotation[] annotations) {
        for(Annotation annotation :annotations){
            if(annotation instanceof RedisCache){
                return (RedisCache) annotation;
            }
        }
        return null;
    }



    /**
     * 对象序列化成json
     *
     * @param target
     * @return
     * @throws Exception
     */
    protected String serialize(Object target) throws Exception {
        return JsonUtils.obj2json(target);
    }

    /**
     * 反序列化
     *
     * @param jsonString json值
     * @param clazz      返回值类型
     * @param modelType  实体类型
     * @return
     * @throws Exception
     */
    protected Object deserialize(String jsonString, Class clazz, Class modelType) throws Exception {
        // 序列化结果应该是List对象
        if ("PageInfo".equals(clazz.getSimpleName())) {
            return JsonUtils.json2Page(jsonString, clazz, modelType);
        } else if (clazz.isAssignableFrom(List.class)) {
            return JsonUtils.json2list(jsonString, modelType);
        }
        // 序列化结果是普通对象
        return JsonUtils.json2pojo(jsonString, clazz);
    }
}
