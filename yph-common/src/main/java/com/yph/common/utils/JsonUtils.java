package com.yph.common.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yph.common.DateConverter.DateConverter;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Administrator Hzhan
 * @create ：2018/1/12
 **/
public class JsonUtils {

    private final static DateConverter dateMapper = new DateConverter();

    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * javaBean,list,array convert to json string
     *
     *   将对象转Json 要将Date 格式化
     */
    public static String obj2json(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * json string convert to javaBean
     */
    public static <T> T json2pojo(String jsonStr, Class<T> clazz) throws Exception {
        return dateMapper.readValue(jsonStr, clazz);
    }

    /**
     * json string convert to map
     */
    public static <T> Map<String, Object> json2map(String jsonStr) throws Exception{
        return objectMapper.readValue(jsonStr, Map.class);
    }

    /**
     * json string convert to map with javaBean
     */


    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) throws Exception{
        Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr,
                new TypeReference<Map<String, T>>() {

                });
        Map<String, T> result = new HashMap<String, T>();
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));

        }
        return result;

    }

    /**
     * json array string convert to list with javaBean
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) throws Exception{
        List<Map<String, Object>> list = dateMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {});
        List<T> result = new ArrayList<T>();
        for (Map<String, Object> map : list) {
            result.add(map2pojo(map, clazz));
        }
        return result;

    }


    /**
     * map convert to javaBean
     */
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return dateMapper.convertValue(map, clazz);
    }


    /**
     *  分页对象的序列化
     * @param jsonString
     * @param modelType
     * @return
     * @throws Exception
     */
    public static Object json2Page(String jsonString, Class clazz,Class modelType) throws Exception {
        PageInfo pageInfo = (PageInfo) json2pojo(jsonString, clazz);
        List<Map<String,Object>> list = pageInfo.getList();

        List<Object> list1 = new ArrayList<>();
        for (Map<String,Object> obj :list) {
            list1.add(map2pojo(obj,modelType));
        }
        pageInfo.setList(list1);
        return pageInfo;
    }
}
