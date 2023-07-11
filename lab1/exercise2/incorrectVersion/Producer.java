package lab1.exercise2.incorrectVersion;

public class Producer implements Runnable {
	MyQueue q;

	public Producer(MyQueue q) {
		this.q = q;
		new Thread(this, "Producer").start();
	}

	@Override
	public void run() {
		int i = 0;
		while (true) {
			q.put(i++);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
	}
}
