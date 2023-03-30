package com.reactiveapp.model.student.gateways;

import com.reactiveapp.model.student.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentGateway {
    Flux<Student> getAllBooks();
    Mono<Student> getStudentById(String id);
    Mono<Student> saveStudent(Student student);
    Mono<Student> updateStudent(Student student);
    Mono<String> deleteStudent(String id);
}
