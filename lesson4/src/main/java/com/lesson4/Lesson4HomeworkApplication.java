package com.example.lesson4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lesson4HomeworkApplication/* implements CommandLineRunner*/ {

    /*@Autowired
    private DatabaseSeeder dbSeeder;*/

	public static void main(String[] args) {
        SpringApplication.run(Lesson4HomeworkApplication.class, args);
	}

    /*@Override
    public void run(String... args) throws Exception {
        dbSeeder.insertData();
    }*/
}
