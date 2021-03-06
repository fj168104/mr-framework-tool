package com.mr.framework.extra.ssh;

import com.mr.framework.core.exceptions.ExceptionUtil;
import com.mr.framework.core.util.StrUtil;

/**
 * Jsch异常
 * @author fengj
 */
public class JschRuntimeException extends RuntimeException{
	private static final long serialVersionUID = 8247610319171014183L;

	public JschRuntimeException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}
	
	public JschRuntimeException(String message) {
		super(message);
	}
	
	public JschRuntimeException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}
	
	public JschRuntimeException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public JschRuntimeException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}
}
