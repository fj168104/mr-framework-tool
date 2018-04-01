package com.mr.framework.cron.task;

/**
 * {@link Runnable} 的 {@link Task}包装
 * @author fengj
 *
 */
public class RunnableTask implements Task{
	private Runnable runnable;
	
	public RunnableTask(Runnable runnable) {
		this.runnable = runnable;
	}

	@Override
	public void execute() {
		runnable.run();
	}
}
