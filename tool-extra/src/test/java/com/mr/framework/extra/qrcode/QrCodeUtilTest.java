package com.mr.framework.extra.qrcode;

import org.junit.Ignore;
import org.junit.Test;

import com.mr.framework.core.io.FileUtil;
import com.mr.framework.core.lang.Console;

/**
 * 二维码工具类单元测试
 * 
 * @author fengj
 *
 */
public class QrCodeUtilTest {

	@Test
	@Ignore
	public void generateTest() {
		QrCodeUtil.generate("http://tool.cn/", 300, 300, FileUtil.file("d:/qrcode.jpg"));
	}

	@Test
	@Ignore
	public void decodeTest() {
		String decode = QrCodeUtil.decode(FileUtil.file("d:/qrcode.jpg"));
		Console.log(decode);
	}
}
