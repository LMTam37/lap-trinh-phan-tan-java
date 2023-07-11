package lab1.exercise1;

public class Yourtask implements Runnable{
	private String taskName;
	private int counter;
	
	
	public Yourtask(String taskName, int counter) {
		this.taskName = taskName;
		this.counter = counter;
	}


	@Override
	public void run() {
		for(int i =0 ;i < counter ; i++) {
			System.out.println(taskName + "#" + i);
		}
	}
}
