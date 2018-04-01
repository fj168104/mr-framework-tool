package com.mr.framework.json;

import org.junit.Assert;
import org.junit.Test;

/**
 * JSON字符串格式化单元测试
 * @author fengj
 *
 */
public class JSONStrFormaterTest {

	@Test
	public void formatTest() {
		String json = "{'age':23,'aihao':['pashan','movies'],'name':{'firstName':'zhang','lastName':'san','aihao':['pashan','movies','name':{'firstName':'zhang','lastName':'san','aihao':['pashan','movies']}]}}";
		String result = JSONStrFormater.format(json);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void formatTest2() {
		String json = "{\"abc\":{\"def\":\"\\\"[ghi]\"}}";
		String result = JSONStrFormater.format(json);
		Assert.assertNotNull(result);
	}
}
