package lab1.exercise2;

public class DaemonThread extends Thread {
	@Override
	public void run() {
		System.out.println("Entering run method");
		try {
			System.out.println("In run Method: currenThead() is" + Thread.currentThread());
			while (true) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
				System.out.println("In run method: woke up again");
			}
		} finally {
			System.out.println("Leaving run method");
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Entering main Method");
		DaemonThread t = new DaemonThread();

		t.setDaemon(true);

		t.start();
		Thread.sleep(3000);
		System.out.println("Leaving main method");
	}
}
