package sol01;

import java.util.List;

public class ServiceGetOrderSubtotal implements Runnable {
	private List<Order> orders;

	public ServiceGetOrderSubtotal(List<Order> orders) {
		this.orders = orders;
	}

	public void run() {
		long total = 0l;
		for(Order order : orders) {
			for(OrderDetails orderDetail: order.getDetails()) {
				total += orderDetail.getUnitPrice();
				System.out.println("Tong so tien cua tat ca order: " + total);
			}
		}
	}

}
