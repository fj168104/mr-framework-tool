package com.mr.framework.extra.qrcode;

import com.mr.framework.core.exceptions.ExceptionUtil;
import com.mr.framework.core.util.StrUtil;

/**
 * Qrcode异常
 * 
 * @author fengj
 */
public class QrCodeException extends RuntimeException {
	private static final long serialVersionUID = 8247610319171014183L;

	public QrCodeException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public QrCodeException(String message) {
		super(message);
	}

	public QrCodeException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}

	public QrCodeException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public QrCodeException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}
}