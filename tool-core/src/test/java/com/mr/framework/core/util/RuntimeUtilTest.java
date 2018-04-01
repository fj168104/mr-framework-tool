package com.mr.framework.core.util;

import org.junit.Ignore;
import org.junit.Test;

import com.mr.framework.core.lang.Console;

/**
 * 命令行单元测试
 * @author fengj
 *
 */
public class RuntimeUtilTest {

	@Test
	@Ignore
	public void execTest() {
		String str = RuntimeUtil.execForStr("ipconfig");
		Console.log(str);
	}

	@Test
	@Ignore
	public void execCmdTest() {
		String str = RuntimeUtil.execForStr("cmd /c dir");
		Console.log(str);
	}
}
