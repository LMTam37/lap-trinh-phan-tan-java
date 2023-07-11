package sol01;

public class OrderDetails {
	private double quanity;
	private double unitPrice;
	private long productId;
	private long detailsId;

	public OrderDetails() {
	}

	public OrderDetails(double quanity, double unitPrice, long productId, long detailsId) {
		this.quanity = quanity;
		this.unitPrice = unitPrice;
		this.productId = productId;
		this.detailsId = detailsId;
	}

	public double getQuanity() {
		return quanity;
	}

	public void setQuanity(double quanity) {
		this.quanity = quanity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(long detailsId) {
		this.detailsId = detailsId;
	}

	@Override
	public String toString() {
		return "OrderDetails [quanity=" + quanity + ", unitPrice=" + unitPrice + ", productId=" + productId
				+ ", detailsId=" + detailsId + "]";
	}

}
