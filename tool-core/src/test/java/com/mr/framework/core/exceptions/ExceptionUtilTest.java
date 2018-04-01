package com.mr.framework.core.exceptions;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.mr.framework.core.io.IORuntimeException;

/**
 * 异常工具单元测试
 * @author fengj
 *
 */
public class ExceptionUtilTest {
	
	@Test
	public void wrapTest() {
		IORuntimeException e = ExceptionUtil.wrap(new IOException(), IORuntimeException.class);
		Assert.assertNotNull(e);
	}
}
