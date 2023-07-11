package lab1.exercise2.correctVersion;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Producer_Consumer_Demo_Fixed {
	public static void main(String[] args) {
		System.out.println("Press Control-C to stop");
		ExecutorService service = Executors.newFixedThreadPool(10);

		MyQueue q = new MyQueue();

		service.execute(new Producer(q));
		service.execute(new Consumer(q));
	}
}
