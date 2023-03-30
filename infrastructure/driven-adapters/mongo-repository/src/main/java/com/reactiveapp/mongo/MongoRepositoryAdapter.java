package com.reactiveapp.mongo;

import com.reactiveapp.model.student.Student;
import com.reactiveapp.model.student.gateways.StudentGateway;
import com.reactiveapp.mongo.data.StudentData;
import com.reactiveapp.mongo.helper.AdapterOperations;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
//public class MongoRepositoryAdapter extends AdapterOperations<Object/* change for domain model */, Object/* change for adapter model */, String, MongoDBRepository>
public class MongoRepositoryAdapter implements StudentGateway {
// implements ModelRepository from domain

    private final MongoDBRepository studentRepository;
    private final ObjectMapper mapper;

    @Override
    public Flux<Student> getAllBooks() {
        return this.studentRepository
                .findAll()
                .switchIfEmpty(Mono.error(new Throwable("No students available")))
                .map(student -> mapper.map(student, Student.class))
                .onErrorResume(Mono::error);
    }

    @Override
    public Mono<Student> getStudentById(String id) {
        return null;
    }

    @Override
    public Mono<Student> saveStudent(Student student) {
        return this.studentRepository
                .save(mapper.map(student, StudentData.class))
                .map(student1 -> mapper.map(student1, Student.class))
                .onErrorResume(Mono::error);
    }

    @Override
    public Mono<Student> updateStudent(Student student) {
        return null;
    }

    @Override
    public Mono<String> deleteStudent(String id) {
        return null;
    }

    //public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper) {
         /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
       // super(repository, mapper, d -> mapper.map(d, Object.class*//* change for domain model *//*));
    //}



}
