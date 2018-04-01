package com.mr.framework.db.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.mr.framework.core.collection.CollectionUtil;
import com.mr.framework.core.lang.Console;
import com.mr.framework.core.thread.ThreadUtil;
import com.mr.framework.db.DbUtil;
import com.mr.framework.db.Entity;
import com.mr.framework.db.SqlRunner;
import com.mr.framework.db.handler.EntityListHandler;
import com.mr.framework.log.LogFactory;
import com.mr.framework.log.dialect.console.ConsoleLogFactory;

/**
 * SqlRunner线程安全测试
 * 
 * @author fengj
 *
 */
@Ignore
public class ConcurentTest {
	
	private SqlRunner runner;
	
	@Before
	public void init() {
		LogFactory.setCurrentLogFactory(new ConsoleLogFactory());
		DbUtil.setShowSqlGlobal(true, false, false);
		runner = SqlRunner.create();
	}
	
	@Test
	public void findTest() {
		for(int i = 0; i < 10000; i++) {
			ThreadUtil.execute(new Runnable() {
				@Override
				public void run() {
					List<Entity> find = null;
					try {
						find = runner.find(CollectionUtil.newArrayList("name AS name2"), Entity.create("user"), new EntityListHandler());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					Console.log(find);
				}
			});
		}
	}
}
