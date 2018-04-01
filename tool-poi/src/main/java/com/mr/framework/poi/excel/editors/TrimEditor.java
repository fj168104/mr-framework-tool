package com.mr.framework.poi.excel.editors;

import org.apache.poi.ss.usermodel.Cell;

import com.mr.framework.core.util.StrUtil;
import com.mr.framework.poi.excel.CellEditor;

/**
 * 去除String类型的单元格值两边的空格
 * @author fengj
 *
 */
public class TrimEditor implements CellEditor{

	@Override
	public Object edit(Cell cell, Object value) {
		if(value instanceof String) {
			return StrUtil.trim((String)value);
		}
		return value;
	}

}
