package lab1.exercise2.correctVersion;

public class Consumer implements Runnable {
	MyQueue q;

	public Consumer(MyQueue q) {
		this.q = q;
	}

	@Override
	public void run() {
		while (true) {
			q.get();
		}
	}
}
