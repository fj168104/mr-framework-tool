package com.mr.framework.cron.pattern.parser;

/**
 * 年值处理
 * @author fengj
 *
 */
public class YearValueParser extends SimpleValueParser{
	
	public YearValueParser() {
		super(1970, 2099);
	}

}
