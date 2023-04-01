package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollDetailDTO {
    @JsonBackReference
    private EnrollDTO enroll;
    @NotNull
    @JsonIncludeProperties(value = {"idCourse","nameCourse"})
    private CourseDTO course;
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20)
    private String classroom;
}
