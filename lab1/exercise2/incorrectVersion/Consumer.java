package lab1.exercise2.incorrectVersion;

public class Consumer implements Runnable {
	MyQueue q;

	public Consumer(MyQueue q) {
		this.q = q;
		new Thread(this, "Consumer").start();
	}

	@Override
	public void run() {
		while (true) {
			q.get();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
	}
}
