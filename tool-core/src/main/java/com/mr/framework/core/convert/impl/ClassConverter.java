package com.mr.framework.core.convert.impl;

import com.mr.framework.core.convert.AbstractConverter;
import com.mr.framework.core.util.ClassUtil;

/**
 * 类转换器<br>
 * 将类名转换为类
 * @author fengj
 *
 */
public class ClassConverter extends AbstractConverter<Class<?>>{
	
	@Override
	protected Class<?> convertInternal(Object value) {
		String valueStr = convertToStr(value);
		try {
			return ClassUtil.getClassLoader().loadClass(valueStr);
		} catch (Exception e) {
			// Ignore Exception
		}
		return null;
	}

}
