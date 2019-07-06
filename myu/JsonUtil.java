package com.meida.shaokaoshop.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * JSON字符串工具类
 */
public class JsonUtil {
	private static ObjectMapper mapper;

	/**
	 * 获取ObjectMapper实例 Inclusion Inclusion.ALWAYS 全部列入 Inclusion
	 * Inclusion.NON_DEFAULT 字段和对象默认值相同的时候不会列入 Inclusion Inclusion.NON_EMPTY
	 * 字段为NULL或者""的时候不会列入 Inclusion Inclusion.NON_NULL 字段为NULL时候不会列入
	 */
	public static synchronized ObjectMapper getMapperInstance() {
		if (mapper == null) {
			mapper = new ObjectMapper();
			// 当找不到对应的序列化器时 忽略此字段
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			// 使Jackson JSON支持Unicode编码非ASCII字符
			SimpleModule module = new SimpleModule();
			module.addSerializer(String.class, new StringUnicodeSerializer());
			mapper.registerModule(module);
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
			mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);// 允许空字符串转换为空对象
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);// 允许空数组转换为空对象
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			// 所有日期格式都统一为以下样式
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
			// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
			// mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES,
			// false);
			// 禁止使用int代表Enum的order()來反序列化Enum,非常危險
			// mapper.configure(Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
			// 设置输出时包含属性的风格
			// 设置null值不参与序列化(字段不被显示)
			mapper.setSerializationInclusion(Include.ALWAYS);
		}
		return mapper;
	}


	/**
	 * 将json字符串转换成java对象
	 *
	 * @param //json准备转换的json字符串
	 * @param //cls准备转换的类
	 */
	public static <T> T jsonToBean(String json, Class<T> cls) {

		ObjectMapper objectMapper = getMapperInstance();
		T object = null;
		try {
			object = objectMapper.readValue(json, cls);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;

	}

}
