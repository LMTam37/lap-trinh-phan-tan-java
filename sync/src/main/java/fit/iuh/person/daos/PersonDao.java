package fit.iuh.person.daos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

import fit.iuh.person.models.Address;
import fit.iuh.person.models.Person;
import fit.iuh.person.models.PhoneNumbers;

public class PersonDao {
	MongoCollection<Document> coll;

	public PersonDao(MongoCollection<Document> coll) {
		this.coll = coll;
	}

	private Address getAddress(Document addDoc) {
		return new Address(addDoc.getString("streetAddress"), addDoc.getString("city"), addDoc.getString("state"),
				Long.parseLong(addDoc.get("postalCode").toString()));
	}

	private List<PhoneNumbers> getListPhoneNumber(List<Document> docs) {
		ArrayList<PhoneNumbers> phoneNumbers = new ArrayList<PhoneNumbers>();
		docs.forEach(t -> {
			phoneNumbers.add(new PhoneNumbers(t.getString("type"), t.getString("number")));
		});
		return phoneNumbers;
	}

	public List<Person> findAll() {
		List<Person> persons = new ArrayList<Person>();
		FindIterable<Document> iter = coll.find();
		iter.forEach(t -> {
			Person person = getPerson(t);
			persons.add(person);
		});
		return persons;
	}

	private Person getPerson(Document doc) {
		Document addDoc = (Document) doc.get("address");
		Address add = getAddress(addDoc);

		List<Document> docs = doc.getList("PhoneNumbers", Document.class);
		List<PhoneNumbers> phoneNumbers = getListPhoneNumber(docs);

		Person person = new Person(doc.getString("firstName"), doc.getString("lastName"), doc.getInteger("age"), add,
				phoneNumbers);
		return person;

//		ArrayList<PhoneNumbers> phoneNumbers = (ArrayList<PhoneNumbers>) result.get("PhoneNumbers");
//
//		Document addDoc = (Document) result.get("address");
//		Address add = new Address(
//				addDoc.getString("streetAddress"), 
//				addDoc.getString("city"),
//				addDoc.getString("state"), 
//				Long.parseLong(addDoc.get("postalCode").toString()));
//
//		Person person = new Person(
//				result.getString("firstName"), 
//				result.getString("lastName"),
//				result.getInteger("age"), 
//				add, 
//				phoneNumbers);
//		return person;
	}

	public void insertOne(Person person) {
		// get Array
		int size = person.getPhoneNumbers().size();
		Document[] docs = new Document[size];
		int i = 0;
		for (PhoneNumbers phoneNumber : person.getPhoneNumbers()) {
			docs[i] = new Document("type", phoneNumber.getType()).append("number", phoneNumber.getNumber());
			i++;
		}

		// insert
		Document doc = new Document("firstName", person.getFirstName()).append("lastName", person.getLastName())
				.append("age", person.getAge())
				.append("address",
						new Document("streetAddress", person.getAddress().getStreetAddress())
								.append("city", person.getAddress().getCity()).append("state", person.getAddress().getState())
								.append("postalCode", person.getAddress().getPostalCode()))
				.append("PhoneNumbers", Arrays.asList(docs));

		coll.insertOne(doc);
	}

	public void deleteOne(String firstName) {
		coll.findOneAndDelete(Filters.eq("firstName", firstName));
	}

	public void updateOne(String firstName, String newFirstName) {
		BasicDBObject newDoc = new BasicDBObject();
		newDoc.append("$set", new BasicDBObject().append("firstName", newFirstName));
		coll.updateOne(Filters.eq("firstName", firstName), newDoc);
	}

	public List<Person> getListPersonByState(String state) {
		List<Person> persons = new ArrayList<Person>();
		FindIterable<Document> iter = coll.find(Filters.eq("address.state", state));
		iter.forEach(person -> {
			persons.add(getPerson(person));
		});

		return persons;
	}

	public List<PhoneNumbers> getPhoneNumbers(String lastName) {
		Document personDoc = coll.find(Filters.eq("firstName", "Jhon")).first();
		Person p = getPerson(personDoc);
		return p.getPhoneNumbers();
	}

	public List<Person> getPersonByAge(int age) {
		List<Person> persons = new ArrayList<Person>();
		FindIterable<Document> iterPerson = coll.find(Filters.gt("age", age));
		iterPerson.forEach(personDoc -> {
			persons.add(getPerson(personDoc));
		});
		return persons;
	}

	public double getStateAvgAge(String state) {
		List<Person> persons = getListPersonByState(state);
		double total = 0.0;
		int size = getStateNumPerson(state);
		for (Person p : persons) {
			total += p.getAge();
		}
		return total / size;
//		coll.aggregate(Arrays.asList(Aggregates.group("$age", Accumulators.avg("age", 1)))).forEach(t -> {
//			System.out.println(t.getDouble("age"));
//		});
	}

	public int getStateNumPerson(String state) {
		List<Person> persons = getListPersonByState(state);
		return persons.size();
	}
}
