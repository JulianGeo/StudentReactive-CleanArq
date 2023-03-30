package com.reactiveapp.api;

import com.reactiveapp.model.student.Student;
import com.reactiveapp.usecase.getallstudents.GetAllStudentsUseCase;
import com.reactiveapp.usecase.savestudent.SaveStudentUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
/*    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET("/api/usecase/path"), handler::listenGETUseCase)
                .andRoute(POST("/api/usecase/otherpath"), handler::listenPOSTUseCase)
                .and(route(GET("/api/otherusercase/path"), handler::listenGETOtherUseCase));
    }*/

    @Bean
    @RouterOperation(path = "/api/students", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllStudentsUseCase.class, method = RequestMethod.GET, beanMethod = "get",
            operation = @Operation(operationId = "getAllStudents", tags = "Student usecases", responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = Student.class))),
                    @ApiResponse(responseCode = "204", description = "Nothing to show")}))
    public RouterFunction<ServerResponse> getAllStudents(GetAllStudentsUseCase getAllStudentsUseCase){
        return route(GET("/api/students"),
                request -> ServerResponse.status(201)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllStudentsUseCase.get(), Student.class))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NO_CONTENT).bodyValue(throwable.getMessage())));
    }

    @Bean
    public RouterFunction<ServerResponse> saveStudent(SaveStudentUseCase saveStudentUseCase){
        return route(POST("/api/students").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Student.class)
                        .flatMap(student -> saveStudentUseCase.apply(student)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).bodyValue(throwable.getMessage()))));
    }

}
