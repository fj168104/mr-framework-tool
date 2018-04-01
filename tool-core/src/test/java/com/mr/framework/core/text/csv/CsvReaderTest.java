package com.mr.framework.core.text.csv;

import java.util.List;

import org.junit.Test;

import com.mr.framework.core.io.resource.ResourceUtil;
import com.mr.framework.core.lang.Console;
import com.mr.framework.core.util.CharsetUtil;

public class CsvReaderTest {
	@Test
	public void readTest() {
		CsvReader reader = new CsvReader();
		CsvData data = reader.read(ResourceUtil.getReader("test.csv", CharsetUtil.CHARSET_UTF_8));
		List<CsvRow> rows = data.getRows();
		for (CsvRow csvRow : rows) {
			Console.log(csvRow.get(2));
		}
	}
}
