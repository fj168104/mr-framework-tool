package com.mr.framework.core.convert.impl;

import java.nio.charset.Charset;

import com.mr.framework.core.convert.AbstractConverter;
import com.mr.framework.core.util.CharsetUtil;

/**
 * 编码对象转换器
 * @author fengj
 *
 */
public class CharsetConverter extends AbstractConverter<Charset>{

	@Override
	protected Charset convertInternal(Object value) {
		return CharsetUtil.charset(convertToStr(value));
	}

}
