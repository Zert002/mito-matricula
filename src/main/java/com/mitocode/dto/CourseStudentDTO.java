package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseStudentDTO {
    @JsonIncludeProperties(value = {"nameCourse","shortNameCourse"})
    CourseDTO course;
    @JsonIncludeProperties(value = {"nameStudent","lastNameStudent","documentIdStudent","ageStudent"})
    List<StudentDTO> students;
}
