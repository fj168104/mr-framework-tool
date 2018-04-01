package com.mr.framework.core.convert.impl;

import java.util.Currency;

import com.mr.framework.core.convert.AbstractConverter;

/**
 * 货币{@link Currency} 转换器
 * 
 * @author fengj
 * @since 3.0.8
 */
public class CurrencyConverter extends AbstractConverter<Currency> {

	@Override
	protected Currency convertInternal(Object value) {
		return Currency.getInstance(value.toString());
	}

}
