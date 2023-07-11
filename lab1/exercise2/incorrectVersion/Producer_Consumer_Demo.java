package lab1.exercise2.incorrectVersion;

public class Producer_Consumer_Demo {
	public static void main(String[] args) {
		MyQueue q = new MyQueue();
		new Producer(q);
		new Consumer(q);
	}
}
