package com.mr.framework.core.convert.impl;

import com.mr.framework.core.convert.AbstractConverter;
import com.mr.framework.core.convert.ConverterRegistry;
import com.mr.framework.core.util.ArrayUtil;

/**
 * byte 类型数组转换器
 * @author fengj
 *
 */
public class ByteArrayConverter extends AbstractConverter<byte[]>{
	
	@Override
	protected byte[] convertInternal(Object value) {
		final Byte[] result = ConverterRegistry.getInstance().convert(Byte[].class, value);
		return ArrayUtil.unWrap(result);
	}

}
