package br.techyfood.restaurantapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static br.techyfood.restaurantapp.utils.RestaurantConstants.BASE_PACKAGE;

@EnableDiscoveryClient
@EntityScan(basePackages = {BASE_PACKAGE})
@EnableJpaRepositories(basePackages = {BASE_PACKAGE})
@SpringBootApplication(scanBasePackages = {BASE_PACKAGE})
public class RestaurantAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantAppApplication.class, args);
    }

}
