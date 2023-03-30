package com.reactiveapp.config;

import com.reactiveapp.model.student.gateways.StudentGateway;
import com.reactiveapp.usecase.getallstudents.GetAllStudentsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.reactiveapp.usecase")/*,
       includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)*/
public class UseCasesConfig {
        @Bean
        public GetAllStudentsUseCase getAllStudentsUseCase(StudentGateway gateway){
        return new GetAllStudentsUseCase(gateway);
        }
}
