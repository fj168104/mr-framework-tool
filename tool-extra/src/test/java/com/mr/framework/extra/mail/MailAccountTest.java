package com.mr.framework.extra.mail;

import org.junit.Assert;
import org.junit.Test;

/**
 * 默认邮件帐户设置测试
 * @author fengj
 *
 */
public class MailAccountTest {
	
	@Test
	public void parseSettingTest() {
		MailAccount account = GlobalMailAccount.INSTANCE.getAccount();
		Assert.assertNotNull(account.getCharset());
	}
}
