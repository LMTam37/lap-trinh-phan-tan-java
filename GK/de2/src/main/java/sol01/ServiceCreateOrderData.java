package sol01;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ServiceCreateOrderData {
	public List<Order> createData() {
		List<Order> orders = new ArrayList<Order>();

		for (int i = 1; i <= 100; i++) {
			Random random = new Random();

			Double unitPrice = random.nextDouble() * 10000;
			if (unitPrice < 1000) {
				unitPrice *= 1000;
			}

			Double quantity = Double.parseDouble(Integer.toString(random.nextInt(100)));
			if (quantity < 10) {
				quantity += 10;
			}

//			Mã nhân viên 50 đơn hàng thuộc là 1 hoặc 2
			String empId = "1";
			if (i > 50) {
				empId = "2";
			}

			OrderDetails orderDetail = new OrderDetails(quantity, unitPrice, random.nextInt(100), random.nextInt(100));
			List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();
			orderDetails.add(orderDetail);
			Date date = new Date(2023 - 1900, random.nextInt(12), random.nextInt(29));
			Order order = new Order(i, empId, orderDetails, date);
			orders.add(order);
		}
		return orders;
	}
}
