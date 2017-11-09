package demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker // SpringCloud abstraction for circuitbreaker provider
public class OrderApplication {
    public static void main(String[] args){
        SpringApplication.run(OrderApplication.class, args);
    }
}
