package com.mr.framework.core.convert.impl;

import com.mr.framework.core.convert.AbstractConverter;

/**
 * 字符串转换器
 * @author fengj
 *
 */
public class StringConverter extends AbstractConverter<String>{

	@Override
	protected String convertInternal(Object value) {
		return convertToStr(value);
	}

}
