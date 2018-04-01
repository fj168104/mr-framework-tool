package com.mr.framework.core.io;

import org.junit.Ignore;
import org.junit.Test;

import com.mr.framework.core.io.file.FileCopier;

/**
 * 文件拷贝单元测试
 * @author fengj
 *
 */
public class FileCopierTest {
	
	@Test
	@Ignore
	public void dirCopyTest() {
		FileCopier copier = FileCopier.create("D:\\Java", "e:/eclipse/eclipse2.zip");
		copier.copy();
	}
	
	@Test
	@Ignore
	public void copyFileToDirTest() {
		FileCopier copier = FileCopier.create("d:/GReen_Soft/XshellXftpPortable.zip", "c:/hp/");
		copier.copy();
	}
}