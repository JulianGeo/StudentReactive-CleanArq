package com.reactiveapp.model.student;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Student {

    private String id;
    private String idNum;
    private String name;
    private String lastname;
    private String email;
    private String plan;
    private Set<String> courses = new HashSet<>();

}
