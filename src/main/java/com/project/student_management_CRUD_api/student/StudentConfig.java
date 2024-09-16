package com.project.student_management_CRUD_api.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

// inject pre-existing data to db
@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student ali = new Student(
                    "Ali",
                    "ali@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 20)
            );
            Student alex = new Student(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, Month.APRIL, 24)
            );

        studentRepository.saveAll(List.of(ali, alex));
        };
    }
}
