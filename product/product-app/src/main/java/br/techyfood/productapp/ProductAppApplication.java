package br.techyfood.productapp;

import br.techyfood.productapp.utils.ProductConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@EntityScan(basePackages = {ProductConstants.BASE_PACKAGE})
@EnableJpaRepositories(basePackages = {ProductConstants.BASE_PACKAGE})
@SpringBootApplication(scanBasePackages = {ProductConstants.BASE_PACKAGE})
public class ProductAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductAppApplication.class, args);
    }

}
