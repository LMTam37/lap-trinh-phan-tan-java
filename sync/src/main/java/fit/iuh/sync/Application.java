package fit.iuh.sync;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import fit.iuh.sync.daos.StudentDao;

public class Application {
	public static void main(String[] args) {
		MongoClient client = MongoClients.create();
		MongoDatabase db = client.getDatabase("student");
		MongoCollection<Document> coll = db.getCollection("student");
		StudentDao dao = new StudentDao(coll);
//		//create
//		for(int i = 0; i < 10; i++) {
//			dao.insertOne(new Student(i, "Nguyen Van "+ i, i + "@gmail.com"));
//		}
		
//		//read
//		dao.findAll().forEach(student ->{
//			System.out.println(student);
//		});
		
//		//update
//		dao.updateEmail(1, "email moi");

//		//delete
//		for(int i = 0; i < 10; i++) {
//			dao.deleteById(i);
//		}
//		dao.deleteAll();
	}
}
