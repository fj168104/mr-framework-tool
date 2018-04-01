package com.mr.framework.extra.mail;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.mr.framework.core.io.FileUtil;

/**
 * 邮件发送测试
 * @author fengj
 *
 */
public class MailTest {
	
	@Test
	@Ignore
	public void sendTest() {
		MailUtil.send("tool@foxmail.com", "测试", "<h1>邮件来自tool测试</h1>", true, FileUtil.file("d:/测试附件文本.txt"));
	}
	
	@Test
	@Ignore
	public void sendHtmlTest() {
		MailUtil.send("tool@foxmail.com", "测试", "<h1>邮件来自tool测试</h1>", true);
	}
	
	@Test
	@Ignore
	public void sendByAccountTest() {
		MailAccount account = new MailAccount();
//		account.setHost("smtp.yeah.net");
//		account.setPort(25);
		account.setFrom("tool@yeah.net");
//		account.setUser("tool");
		account.setPass("q1w2e3");
//		MailUtil.send(account, "914104645@qq.com, loolly@aliyun.com", "测试", "邮件来自tool测试", true);
	}
	
	@Test
	public void mailAccountTest() {
		MailAccount account = new MailAccount();
		account.setFrom("tool@yeah.net");
		account.setDebug(true);
		account.defaultIfEmpty();
		Properties props = account.getSmtpProps();
		Assert.assertEquals("true", props.getProperty("mail.debug"));
	}
}
