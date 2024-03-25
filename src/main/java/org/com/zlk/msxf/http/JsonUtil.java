package org.com.zlk.msxf.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 对象转json，json转对象
 * @author 会游泳的蚂蚁
 * @date 2024/3/23 10:27
 */
public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        //对象转json，输出所有字段，不管是否为空
        MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //json解析为对象时，属性不存在时不报错
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //解析json时，允许不带引号的 字段名
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //解析json时，允许单引号
        MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //解析json时，数组中允许数据丢失，解析为null
        MAPPER.configure(JsonParser.Feature.ALLOW_MISSING_VALUES, true);

        MAPPER.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static ObjectMapper getObjectMapper() {
        return MAPPER;
    }

    public static String toJsonString(Object obj) {
        return toJsonString(obj, false);
    }

    public static String toJsonString(Object obj, boolean pretty) {
        return toJsonString(obj, "{}", pretty);
    }


    public static String toJsonString(Object obj, String defaultStrIfNull) {
        return toJsonString(obj, defaultStrIfNull, false);
    }


    public static <T> T toObject(String jsonStr, Class<T> cls) {
        return toObject(jsonStr, cls, null);
    }

    public static <T> T toObject(byte[] bytes, Class<T> cls) {
        try {
            return MAPPER.readValue(bytes, cls);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> T toList(String jsonStr, Class<T> cls) {
        if (!StringUtil.hasText(jsonStr)) {
            return null;
        }
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, cls);
        try {
            return MAPPER.readValue(jsonStr, javaType);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }


    public static <T> LinkedHashMap<String, T> toMap(String jsonStr) {
        if (!StringUtil.hasText(jsonStr)) {
            return new LinkedHashMap<>(0);
        }
        try {
            return MAPPER.readValue(jsonStr, LinkedHashMap.class);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            return new LinkedHashMap<>(0);
        }
    }


    public static JsonNode toJsonNode(String jsonStr) {
        try {
            return MAPPER.readTree(jsonStr);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static ObjectNode toObjectNode(String jsonStr) {
        try {
            return (ObjectNode) MAPPER.readTree(jsonStr);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }


    private static String toJsonString(Object obj, String defaultStrIfNull, boolean pretty) {
        if (null == obj) {
            return defaultStrIfNull;
        }
        try {
            if (pretty) {
                return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            } else {
                return MAPPER.writeValueAsString(obj);
            }
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            return defaultStrIfNull;
        }
    }

    private static <T> T toObject(String jsonStr, Class<T> cls, T defaultValue) {
        if (!StringUtil.hasText(jsonStr)) {
            return defaultValue;
        }
        try {
            return MAPPER.readValue(jsonStr, cls);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            return defaultValue;
        }
    }


}
