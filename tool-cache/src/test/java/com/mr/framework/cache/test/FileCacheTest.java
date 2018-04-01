package com.mr.framework.cache.test;

import org.junit.Assert;
import org.junit.Test;

import com.mr.framework.cache.file.LFUFileCache;

/**
 * 文件缓存单元测试
 * @author fengj
 *
 */
public class FileCacheTest {
	@Test
	public void lfuFileCacheTest() {
		LFUFileCache cache = new LFUFileCache(1000, 500, 2000);
		Assert.assertNotNull(cache);
	}
}
