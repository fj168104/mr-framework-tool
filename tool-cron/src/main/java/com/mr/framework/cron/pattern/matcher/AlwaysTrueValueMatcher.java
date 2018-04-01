package com.mr.framework.cron.pattern.matcher;

import com.mr.framework.core.util.StrUtil;

/**
 * 值匹配，始终返回<code>true</code>
 * @author fengj
 *
 */
public class AlwaysTrueValueMatcher implements ValueMatcher{

	@Override
	public boolean match(Integer t) {
		return true;
	}
	
	@Override
	public String toString() {
		return StrUtil.format("[Matcher]: always true.");
	}
}
