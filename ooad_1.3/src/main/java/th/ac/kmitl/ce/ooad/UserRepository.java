package th.ac.kmitl.ce.ooad;

/**
 * Created by Nut on 10/12/2015.
 */

import java.util.UUID;

import com.mongodb.Block;
import com.mongodb.MongoClient;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class UserRepository {
    MongoClient mongoClient;
    MongoDatabase db;
    boolean exist = false;
    private String p = "";
    public UserRepository(){
        mongoClient = new MongoClient();
        db = mongoClient.getDatabase("UserRepository");
    }

    public String addUser(Account newAccount){
        String userId = null;
        if(!isExist(newAccount.getUsername())){
            userId = UUID.randomUUID().toString();
            Document data = new Document("usrname", newAccount.getUsername())
                    .append("pwd", newAccount.getPassphrase())
                    .append("name", newAccount.getProfile().name)
                    .append("email", newAccount.getProfile().email)
                    .append("imgLoc", newAccount.getProfile().imgLoc)
                    .append("userId", userId);
            db.getCollection("Users").insertOne(data);
        }
        return userId;
    }

    public boolean updatePasswd(String userId, String passphrase){
        if(isExistById(userId)){
            db.getCollection("Users").updateOne(eq("userId", userId), new Document("$set", new Document("pwd", passphrase)));
            return true;
        }
        return false;
    }

    public void updateEmail(String userId, String email){

    }

    public void updateProImage(String userId, String imgLoc){

    }

    public void updateName(String userId, String name){

    }

    public String getUserIdByEmail(String email){
        return "username";
    }

    public boolean isExist(String usrname){
        boolean tmp = false;
        FindIterable<Document> cursor = db.getCollection("Users").find(new Document("usrname", usrname));
        cursor.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                if(document.containsValue(usrname)) exist = true;
            }
        });
        tmp = exist;
        exist = false;
        return tmp;
    }

    public boolean isExistById(String userId){
        boolean tmp = false;
        FindIterable<Document> cursor = db.getCollection("Users").find(new Document("userId", userId));
        cursor.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                if(document.containsValue(userId)) exist = true;
            }
        });
        tmp = exist;
        exist = false;
        return tmp;
    }

    protected String getUserPass(String usrname){
        String tmp = "";
        FindIterable<Document> cursor = db.getCollection("Users").find(new Document("usrname", usrname));
        cursor.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                if(document.containsValue(usrname)) p = document.getString("pwd");
            }
        });
        tmp = p;
        p = "";
        return tmp;
    }
}
