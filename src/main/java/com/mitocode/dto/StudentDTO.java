package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {
    private Integer idStudent;
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String nameStudent;
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String lastNameStudent;
    @NotNull
    @NotEmpty
    @Size(min = 8, max = 8)
    private String documentIdStudent;

    @NotNull
    @Min(value = 0)
    @Max(value = 120)
    private Integer ageStudent;
}
