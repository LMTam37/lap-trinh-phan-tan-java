package sol01;

import java.util.List;

public class ServiceGetEmpSubtotal implements Runnable {

	private List<Order> orders;
	private String empId;

	public ServiceGetEmpSubtotal(List<Order> orders, String empId) {
		this.orders = orders;
		this.empId = empId;
	}

	public void run() {
		long total = 0l;
		boolean flag = false;
		for (Order order : orders) {
			if (order.getEmployeeId().equals(empId)) {
				flag = true;
				for (OrderDetails orderDetail : order.getDetails()) {
					total += orderDetail.getUnitPrice();
					System.out.println("Tong so tien cua nhan vien " + empId + ": " + total);
				}
			}
		}
		if(flag == false) {
			System.out.println("Không tồn tại nhân viên có mã " + empId);
		}
	}

}
