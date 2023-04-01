package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO {
    private Integer idCourse;
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String nameCourse;
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20)
        private String shortNameCourse;
    @NotNull
    private boolean enabledCourse;
}
