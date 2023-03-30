package com.reactiveapp.usecase.savestudent;

import com.reactiveapp.model.student.Student;
import com.reactiveapp.model.student.gateways.StudentGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveStudentUseCase implements Function<Student,Mono<Student>> {

    private final StudentGateway gateway;

    @Override
    public Mono<Student> apply(Student student) {
        return gateway.saveStudent(student);
    }

}
