package com.mr.framework.db.test;

import org.junit.Assert;
import org.junit.Test;

import com.mr.framework.db.Entity;
import com.mr.framework.db.test.pojo.User;

/**
 * Entity测试
 * 
 * @author fengj
 *
 */
public class EntityTest {
	
	@Test
	public void parseTest() {
		User user = new User();
		user.setId(1);
		user.setName("test");
		
		Entity entity = Entity.create("testTable").parseBean(user);
		Assert.assertEquals(Integer.valueOf(1), entity.getInt("id"));
		Assert.assertEquals("test", entity.getStr("name"));
	}
	
	@Test
	public void entityToBeanIgnoreCaseTest() {
		Entity entity = Entity.create().set("ID", 2).set("NAME", "testName");
		User user = entity.toBeanIgnoreCase(User.class);
		
		Assert.assertEquals(Integer.valueOf(2), user.getId());
		Assert.assertEquals("testName", user.getName());
	}
}
