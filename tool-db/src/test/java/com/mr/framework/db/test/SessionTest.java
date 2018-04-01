package com.mr.framework.db.test;

import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

import com.mr.framework.core.lang.VoidFunc;
import com.mr.framework.db.Entity;
import com.mr.framework.db.Session;

/**
 * 事务性数据库操作单元测试
 * @author fengj
 *
 */
public class SessionTest {
	
	@Test
	@Ignore
	public void transTest() {
		Session session = Session.create();
		try {
			session.beginTransaction();
			session.update(Entity.create().set("age", 76), Entity.create("user").set("name", "unitTestUser"));
			session.commit();
		} catch (SQLException e) {
			session.quietRollback();
		}
	}
	
	@Test
	@Ignore
	public void transFuncTest() {
		final Session session = Session.create();
		session.trans(new VoidFunc() {
			@Override
			public void call() throws SQLException {
				session.update(Entity.create().set("age", 78), Entity.create("user").set("name", "unitTestUser"));
			}
		});
	}
}
