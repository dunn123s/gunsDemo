package com.xsl.crm.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class PojoUtils {
	
	/**
	 * 
	 * @param source
	 * @param target
	 * @param ignoreNullValue 是否忽略属性空值
	 * @param editable
	 * @param ignoreProperties
	 * @throws BeansException
	 */
	private static void copyProperties(Object source, Object target, boolean ignoreNullValue, @Nullable Class<?> editable,
			@Nullable String... ignoreProperties) throws BeansException {

		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		Class<?> actualEditable = target.getClass();
		if (editable != null) {
			if (!editable.isInstance(target)) {
				throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
						"] not assignable to Editable class [" + editable.getName() + "]");
			}
			actualEditable = editable;
		}
		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
				PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null &&
							ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = readMethod.invoke(source);
							if(ignoreNullValue && value == null) continue;
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, value);
						}
						catch (Throwable ex) {
							throw new FatalBeanException(
									"Could not copy property '" + targetPd.getName() + "' from source to target", ex);
						}
					}
				}
			}
		}
	}

	public final static void copy(Object target, Object source, String... ignoreProperties) {
		copy(target, source, false, ignoreProperties);
		
	}
	
	public final static void copy(Object target, Object source, Boolean ignoreNullValue, String... ignoreProperties) {
		try {
			copyProperties(source ,target, ignoreNullValue, null, ignoreProperties);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public final static <T> T clone(Class<T> targetClass, Object source, String... ignoreProperties) {
		try {
			T target = targetClass.newInstance();
			copy(target, source, ignoreProperties);
			return target;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public final static <T> List<T> clone(Class<T> targetClass, List<?> sources, String... ignoreProperties) {
		try {
			List<T> result = sources.stream().map(source -> {
				return clone(targetClass, source, ignoreProperties);
			}).collect(Collectors.toList());
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
