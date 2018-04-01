package com.mr.framework.log.dialect.console;

import com.mr.framework.log.Log;
import com.mr.framework.log.LogFactory;

/**
 * 利用System.out.println()打印日志
 * @author fengj
 *
 */
public class ConsoleLogFactory extends LogFactory {
	
	public ConsoleLogFactory() {
		super("tool Console Logging");
	}

	@Override
	public Log createLog(String name) {
		return new ConsoleLog(name);
	}

	@Override
	public Log createLog(Class<?> clazz) {
		return new ConsoleLog(clazz);
	}

}
