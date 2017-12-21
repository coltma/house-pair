package demo.config;

import com.mongodb.MongoClient;
import demo.template.MyMongoOps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;

@Configuration
public class AppConfig {

//    @Bean
//    public MongoOperations myBean() throws UnknownHostException {
//        // instantiate, configure and return bean ...
//        return new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "test"));
//
//    }
}
