package sol02;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import sol01.ServiceCreateOrderData;
import sol01.Order;

public class Application {
	public static void main(String[] args) throws Exception {
		MongoClient client = MongoClients.create();
		MongoDatabase db = client.getDatabase("sales");
		MongoCollection<Document> coll = db.getCollection("orders");
		//Lấy danh sách data ngẫu nhiên
		List<Order> orders = new ServiceCreateOrderData().createData();
		
		SaleDao dao = new SaleDao(coll);
		//lưu data vào mongodb
		dao.insert(orders);
		//lưu vào file data.json
		try(Writer write = new FileWriter("data.json")){
			Gson gson = new GsonBuilder().create();
			gson.toJson(orders, write);
		}
	}
}
