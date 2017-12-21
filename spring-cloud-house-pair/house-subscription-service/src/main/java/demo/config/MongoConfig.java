package demo.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;
//
//@Configuration
//public class MongoConfig {
//    public @Bean
//    MongoClient mongoClient() throws UnknownHostException {
//        return new MongoClient("localhost");
//    }
//
//    public @Bean
//    MongoTemplate mongoTemplate() throws UnknownHostException {
//        return new MongoTemplate(mongoClient(), "test");
//    }
//}
