package sol01;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
	public static void main(String[] args) {

		List<Order> orders = new ArrayList<Order>();
		//Lấy danh sách data ngẫu nhiên
		orders = new ServiceCreateOrderData().createData();
		//Tạo Executor service
		ExecutorService service = Executors.newFixedThreadPool(2);
		//Tính tổng số tiền của tất cả order
		service.execute(new ServiceGetOrderSubtotal(orders));
		//Tính tổng số tiền nhân viên mã số 1 bán được
		service.execute(new ServiceGetEmpSubtotal(orders, "1"));
		service.shutdown();
	}
}
