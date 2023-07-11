package fit.iuh.sync.daos;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import fit.iuh.sync.models.Student;

public class StudentDao {

	MongoCollection<Document> coll;

	public StudentDao(MongoCollection<Document> coll) {
		this.coll = coll;
	}

	public Student findById(long id) {
		Document doc = coll.find(Filters.eq("id", id)).first();
		return new Student(doc.getLong("id"), doc.getString("name"), doc.getString("email"));
	}

	public List<Student> findAll() {
		List<Student> students = new ArrayList<Student>();
		FindIterable<Document> iter = coll.find();
		iter.forEach(doc -> {
			students.add(new Student(doc.getLong("id"), doc.getString("name"), doc.getString("email")));
		});
		return students;
	}

	public void insertOne(Student student) {
		Document doc = new Document("id", student.getId()).append("name", student.getName()).append("email",
				student.getEmail());
		coll.insertOne(doc);
	}

	public void insertMany(List<Student> students) {
		List<Document> docs = new ArrayList<Document>();
		students.forEach(student -> {
			docs.add(new Document("id", student.getId()).append("name", student.getName()).append("email",
					student.getEmail()));
		});
		coll.insertMany(docs);
	}

	public void deleteById(long id) { 
//		 BasicDBObject filter = new BasicDBObject().append("id", id); 
//		 coll.deleteMany(filter);

		coll.findOneAndDelete(Filters.eq("id", id));
	}

	public void deleteAll() {
		coll.deleteMany(Filters.empty());
	}

	public void updateEmail(long id, String newEmail) {
		BasicDBObject newDoc = new BasicDBObject();
		newDoc.append("$set", new BasicDBObject().append("email", newEmail));
		BasicDBObject filter = new BasicDBObject().append("id", id);
		coll.updateMany(filter, newDoc);

	}

}
