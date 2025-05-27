package com.wukong.uid;

import com.wukong.uid.service.UidGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UidTestApplication implements CommandLineRunner {

    @Autowired
    private UidGeneratorService uidGeneratorService;

    public static void main(String[] args) {
        SpringApplication.run(UidTestApplication.class, args);
    }

    @Override
    public void run(String... args) {
        for (int i = 0; i < 10; i++) {
            long id = uidGeneratorService.getId("product");
            System.out.println("发号: " + id);
        }
    }
}
