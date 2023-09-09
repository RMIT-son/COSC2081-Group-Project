# COSC2081-Group-Project
Final Assignment for the course COSC2081 - Programming 1 at RMIT Vietnam:  
A Container Port Logistics Management system done by Group 16 

## Draft Diagram</h1>
<iframe frameborder="0" style="width:100%;height:779px;" src="https://viewer.diagrams.net/?tags=%7B%7D&highlight=0000ff&edit=_blank&layers=1&nav=1#G1FASyFOazKLUvPloxsHQ4VSH5cfhjaLrI"></iframe>

## Link to the diagram:
[Diagram Link](https://drive.google.com/file/d/1FASyFOazKLUvPloxsHQ4VSH5cfhjaLrI/view?usp=sharing)

Use this link to enter the diagram and edit it with your method names and changes to the project.
## MongoDB Connections
URI: mongodb+srv://root:pwd12345@cosc2081.vkez09i.mongodb.net/?retryWrites=true&w=majority  
[This is an example of CRUD with MongoDB in Java](https://www.mongodb.com/developer/languages/java/java-setup-crud-operations/)  
[Documentation Link](https://www.mongodb.com/docs/drivers/java/sync/current/quick-start/)  

## Sample MongoDB Document Parsing
```java
Document bsonDocument = new Document("_id", new ObjectId("6157a7b7fca4af7d822a667b"))
    .append("name", "John Doe")
    .append("age", 30);
```  

```java
public class Person {
    private ObjectId id;
    private String name;
    private int age;

    // Getters and setters
}
```  

```java
import org.bson.Document;
import org.bson.types.ObjectId;

public class BSONConverter {

    public static Person convertToPerson(Document bsonDocument) {
        Person person = new Person();
        person.setId(bsonDocument.getObjectId("_id"));
        person.setName(bsonDocument.getString("name"));
        person.setAge(bsonDocument.getInteger("age"));

        return person;
    }

    public static void main(String[] args) {
        Document bsonDocument = new Document("_id", new ObjectId("6157a7b7fca4af7d822a667b"))
            .append("name", "John Doe")
            .append("age", 30);

        Person person = convertToPerson(bsonDocument);

        System.out.println(person.getName()); // Output: John Doe
        System.out.println(person.getAge());  // Output: 30
    }
}
```  


