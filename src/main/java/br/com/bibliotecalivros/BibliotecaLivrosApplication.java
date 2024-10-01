package br.com.bibliotecalivros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "br.com.bibliotecalivros.repository")
public class BibliotecaLivrosApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaLivrosApplication.class, args);
    }
}