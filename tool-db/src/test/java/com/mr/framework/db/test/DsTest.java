package com.mr.framework.db.test;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;

import com.mr.framework.core.collection.CollUtil;
import com.mr.framework.db.Entity;
import com.mr.framework.db.SqlRunner;
import com.mr.framework.db.ds.DSFactory;
import com.mr.framework.db.ds.c3p0.C3p0DSFactory;
import com.mr.framework.db.ds.dbcp.DbcpDSFactory;
import com.mr.framework.db.ds.druid.DruidDSFactory;
import com.mr.framework.db.ds.hikari.HikariDSFactory;
import com.mr.framework.db.ds.pooled.PooledDSFactory;
import com.mr.framework.db.ds.tomcat.TomcatDSFactory;

/**
 * 数据源单元测试
 * @author fengj
 *
 */
public class DsTest {
	
	@Test
	public void defaultDsTest() throws SQLException{
		DataSource ds = DSFactory.get();
		SqlRunner runner = SqlRunner.create(ds);
		List<Entity> all = runner.findAll("user");
		Assert.assertTrue(CollUtil.isNotEmpty(all));
	}
	
	@Test
	public void hikariDsTest() throws SQLException{
		DSFactory.setCurrentDSFactory(new HikariDSFactory());
		DataSource ds = DSFactory.get();
		SqlRunner runner = SqlRunner.create(ds);
		List<Entity> all = runner.findAll("user");
		Assert.assertTrue(CollUtil.isNotEmpty(all));
	}
	
	@Test
	public void druidDsTest() throws SQLException{
		DSFactory.setCurrentDSFactory(new DruidDSFactory());
		DataSource ds = DSFactory.get();
		
		SqlRunner runner = SqlRunner.create(ds);
		List<Entity> all = runner.findAll("user");
		Assert.assertTrue(CollUtil.isNotEmpty(all));
	}
	
	@Test
	public void tomcatDsTest() throws SQLException{
		DSFactory.setCurrentDSFactory(new TomcatDSFactory());
		DataSource ds = DSFactory.get();
		SqlRunner runner = SqlRunner.create(ds);
		List<Entity> all = runner.findAll("user");
		Assert.assertTrue(CollUtil.isNotEmpty(all));
	}
	
	@Test
	public void dbcpDsTest() throws SQLException{
		DSFactory.setCurrentDSFactory(new DbcpDSFactory());
		DataSource ds = DSFactory.get();
		SqlRunner runner = SqlRunner.create(ds);
		List<Entity> all = runner.findAll("user");
		Assert.assertTrue(CollUtil.isNotEmpty(all));
	}
	
	@Test
	public void c3p0DsTest() throws SQLException{
		DSFactory.setCurrentDSFactory(new C3p0DSFactory());
		DataSource ds = DSFactory.get();
		SqlRunner runner = SqlRunner.create(ds);
		List<Entity> all = runner.findAll("user");
		Assert.assertTrue(CollUtil.isNotEmpty(all));
	}
	
	@Test
	public void toolPoolTest() throws SQLException{
		DSFactory.setCurrentDSFactory(new PooledDSFactory());
		DataSource ds = DSFactory.get();
		SqlRunner runner = SqlRunner.create(ds);
		List<Entity> all = runner.findAll("user");
		Assert.assertTrue(CollUtil.isNotEmpty(all));
	}
}
