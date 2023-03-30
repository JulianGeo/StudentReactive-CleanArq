package com.reactiveapp.mongo;

import com.reactiveapp.mongo.data.StudentData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBRepository extends ReactiveMongoRepository<StudentData, String> {
}
