package com.hui.common.utils;

import java.lang.reflect.Field;

public class ConvertObjectField {
	public static void convertNullValue2Empty(Object obj){
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (field.get(obj)== null) {
					field.set(obj, "");
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
