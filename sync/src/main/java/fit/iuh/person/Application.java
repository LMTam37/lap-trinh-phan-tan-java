package fit.iuh.person;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import fit.iuh.person.daos.PersonDao;
import fit.iuh.person.models.Address;
import fit.iuh.person.models.Person;
import fit.iuh.person.models.PhoneNumbers;

public class Application {
	public static void main(String[] args) {
		MongoClient client = MongoClients.create();
		MongoDatabase db = client.getDatabase("person");
		MongoCollection<Document> coll = db.getCollection("person");
		PersonDao dao = new PersonDao(coll);
//		//insert
		List<PhoneNumbers> phoneNumbers = new ArrayList<PhoneNumbers>();
		phoneNumbers.add(new PhoneNumbers("home", "212 555-1234"));
		phoneNumbers.add(new PhoneNumbers("fax", "646 555-4567"));
		Person person = new Person("Jhon", "Smith", 25, new Address("21 2nd Street", "New York", "NY", 10021),
				phoneNumbers);
		dao.insertOne(person);
		
//		System.out.println(dao.getListPersonByState("NY"));

//		System.out.println(dao.getPhoneNumbers("Jhon"));

//		dao.getPersonByAge(50).forEach(t->{
//			System.out.println(t);
//		}) 

//		System.out.println(dao.getStateAvgAge("NY"));

//		System.out.println(dao.getStateNumPerson("NY"));
		;
	}
}
