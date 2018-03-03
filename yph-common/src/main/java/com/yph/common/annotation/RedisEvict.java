package com.yph.common.annotation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface RedisEvict {

    /**
     *  类型
     * @return
     */
    Class type();

    /**
     *  标记要清理的缓存
     * @return
     */
    String[] evicts() default {};

}