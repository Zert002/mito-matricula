package com.mitocode.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Course {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCourse;
    @Column(length = 50, nullable = false)
    private String courseName;
    @Column(length = 20, nullable = false)
    private String shortName;
    @Column(nullable = false)
    private boolean enabled;
}
