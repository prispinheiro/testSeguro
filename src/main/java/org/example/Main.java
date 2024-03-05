package org.example;

import org.example.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class Main {

    @Autowired
    ProdutoRepository produtoRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}