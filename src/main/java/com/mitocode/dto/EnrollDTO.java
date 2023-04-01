package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollDTO {
    private Integer idEnroll;
    @NotNull
    private LocalDateTime enrollDate;
    @NotNull
    @JsonIncludeProperties(value = {"idStudent","nameStudent","lastNameStudent"})
    private StudentDTO student;
    @NotNull
    private boolean enabledEnroll;
    @NotNull
    @JsonManagedReference
    private List<EnrollDetailDTO> details;
}
