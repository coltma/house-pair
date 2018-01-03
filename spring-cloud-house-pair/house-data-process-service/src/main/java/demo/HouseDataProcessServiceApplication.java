package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HouseDataProcessServiceApplication {



//    @Bean
//    public RabbitMQReceiver receiver() {
//        return new RabbitMQReceiver();
//    }

    public static void main(String[] args){
        SpringApplication.run(HouseDataProcessServiceApplication.class, args);
    }
}
