package com.warthur.demo.test.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public final class ClassUtil {

	/**
	 * 输出对象信息
	 *
	 * @param object
	 */
	public static void printClassMessage(Object object) {
		Class clazz = object.getClass();

		System.out.println("类名称：" + clazz.getSimpleName());

		Method[] methods = clazz.getDeclaredMethods();
		if (methods.length > 0) {
			for (Method method : methods) {
				System.out.println("    方法名：" + method.getName());

				Parameter[] parameter = method.getParameters();
				for (Parameter param : parameter) {
					System.out.println("        参数类型：" + param.getType().getSimpleName() + " 参数名称：" + param.getName());
				}

				System.out.println("    返回值类型：" + method.getReturnType().getSimpleName());
			}
		}

		Field[] fields = clazz.getDeclaredFields();
		if (fields.length > 0) {
			for (Field field : fields) {
				System.out.println("属性类型：" + field.getType().getSimpleName() + " 名称：" + field.getName());
			}
		}
	}
}
