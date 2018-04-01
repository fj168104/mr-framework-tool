package com.mr.framework.core.convert.impl;

import java.util.TimeZone;

import com.mr.framework.core.convert.AbstractConverter;

/**
 * TimeZone转换器
 * @author fengj
 *
 */
public class TimeZoneConverter extends AbstractConverter<TimeZone>{

	@Override
	protected TimeZone convertInternal(Object value) {
		return TimeZone.getTimeZone(convertToStr(value));
	}

}
