package lab1.exercise1;

public class TaskExecute {
	public static void main(String[] args) {
		Runnable r1 = new Yourtask("Print task", 20);
		Runnable r2 = new Yourtask("Distribute Task", 20);
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
	}
}
