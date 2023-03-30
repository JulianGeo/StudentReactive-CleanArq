package com.reactiveapp.usecase.getallstudents;

import com.reactiveapp.model.student.Student;
import com.reactiveapp.model.student.gateways.StudentGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllStudentsUseCase {
    private final StudentGateway gateway;
    public Flux<Student> get(){
        return gateway.getAllBooks();
    }
}
