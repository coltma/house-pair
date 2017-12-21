package demo.template;

import com.mongodb.MongoClient;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;

public class MyMongoOps {
    public MongoOperations mongoOps;
    public MyMongoOps() throws UnknownHostException {
        this.mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "test"));
    }
}
