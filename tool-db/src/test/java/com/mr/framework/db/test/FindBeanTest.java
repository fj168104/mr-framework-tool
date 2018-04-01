package com.mr.framework.db.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mr.framework.db.Entity;
import com.mr.framework.db.SqlRunner;
import com.mr.framework.db.test.pojo.User;

/**
 * Entity测试
 * 
 * @author fengj
 *
 */
public class FindBeanTest {

	SqlRunner runner;

	@Before
	public void init() {
		runner = SqlRunner.create();
	}

	@Test
	public void findAllBeanTest() throws SQLException {
		List<User> results = runner.findAll(Entity.create("user"), User.class);
		
		Assert.assertEquals(3, results.size());
		Assert.assertEquals(Integer.valueOf(1), results.get(0).getId());
		Assert.assertEquals("张三", results.get(0).getName());
	}
	
	@Test
	@SuppressWarnings("rawtypes")
	public void findAllListTest() throws SQLException {
		List<List> results = runner.findAll(Entity.create("user"), List.class);
		
		Assert.assertEquals(3, results.size());
		Assert.assertEquals(1, results.get(0).get(0));
		Assert.assertEquals("张三", results.get(0).get(1));
	}
	
	@Test
	public void findAllArrayTest() throws SQLException {
		List<Object[]> results = runner.findAll(Entity.create("user"), Object[].class);
		
		Assert.assertEquals(3, results.size());
		Assert.assertEquals(1, results.get(0)[0]);
		Assert.assertEquals("张三", results.get(0)[1]);
	}
	
	@Test
	public void findAllStringTest() throws SQLException {
		List<String> results = runner.findAll(Entity.create("user"), String.class);
		Assert.assertEquals(3, results.size());
	}
	
	@Test
	public void findAllStringArrayTest() throws SQLException {
		List<String[]> results = runner.findAll(Entity.create("user"), String[].class);
		
		Assert.assertEquals(3, results.size());
		Assert.assertEquals("1", results.get(0)[0]);
		Assert.assertEquals("张三", results.get(0)[1]);
	}
}
