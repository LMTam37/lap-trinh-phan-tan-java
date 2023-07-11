package sol02;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertManyResult;

import sol01.Order;
import sol01.OrderDetails;

public class SaleDao {
	MongoCollection<Document> coll;

	public SaleDao(MongoCollection<Document> coll) {
		this.coll = coll;
	}

	public Document toDocument(Order order) {
		List<Document> orderDetails = new ArrayList<Document>();

		for (OrderDetails orderDetail : order.getDetails()) {
			orderDetails.add(new Document("quantity", orderDetail.getQuanity())
					.append("unitPrice", orderDetail.getUnitPrice()).append("productId", orderDetail.getProductId())
					.append("detailsId", orderDetail.getDetailsId()));
		}

		Document doc = new Document("id", order.getId()).append("employeeId", order.getEmployeeId())
				.append("details", orderDetails).append("cretaeDate", order.getCretaeDate());

		return doc;
	}

	public boolean insert(List<Order> orders) {
		List<Document> docs = new ArrayList<Document>();
		orders.forEach(order -> {
			docs.add(toDocument(order));
		});
		InsertManyResult result = coll.insertMany(docs);
		return result.getInsertedIds() != null;
	}
}
