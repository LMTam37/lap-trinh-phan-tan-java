package lab1.exercise1;

import java.util.concurrent.Callable;

public class ComputationTask implements Callable<Long> {
	private String taskName;

	public ComputationTask(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public Long call() throws Exception {
		Long result = 0l;
		for (int i = 0; i < 1000; i++) {
			result += i;
			System.out.println(taskName + "#" + i);
			Thread.sleep(10);
		}
		return result;
	}

}
