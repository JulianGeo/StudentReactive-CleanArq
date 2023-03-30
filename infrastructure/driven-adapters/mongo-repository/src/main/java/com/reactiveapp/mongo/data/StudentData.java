package com.reactiveapp.mongo.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Document(collection = "students")
@NoArgsConstructor
@AllArgsConstructor
public class StudentData {

    @Id
    private String id =UUID.randomUUID().toString().substring(0,10);
    private String idNum;
    @NotBlank(message="Empty field error")
    @NotNull(message ="name is required")
    @Pattern(regexp="^[A-Z][a-z]*$", message="name format is required")
    private String name;
    private String lastname;
    private String email;
    private String plan;
    private Set<String> courses = new HashSet<>();
}
