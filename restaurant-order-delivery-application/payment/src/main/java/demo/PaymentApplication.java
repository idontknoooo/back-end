package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
@EnableDiscoveryClient
@EnableCircuitBreaker
public class PaymentApplication {
    public static void main(String[] args){
        SpringApplication.run(PaymentApplication.class, args);
    }
}
