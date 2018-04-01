package com.mr.framework.db.test;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;

import com.mr.framework.core.collection.CollectionUtil;
import com.mr.framework.db.DbUtil;
import com.mr.framework.db.ds.DSFactory;
import com.mr.framework.db.meta.Table;

/**
 * 元数据信息单元测试
 * 
 * @author fengj
 *
 */
public class MetaTest {
	DataSource ds = DSFactory.get();

	@Test
	public void getTablesTest() {
		List<String> tables = DbUtil.getTables(ds);
		Assert.assertEquals("user", tables.get(0));
	}

	@Test
	public void getTableMetaTest() {

		Table table = DbUtil.getTableMeta(ds, "user");
		Assert.assertEquals(CollectionUtil.newHashSet("id"), table.getPkNames());
	}
}
