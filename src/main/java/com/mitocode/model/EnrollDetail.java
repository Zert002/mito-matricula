package com.mitocode.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class EnrollDetail {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnrollDetaill;
    @ManyToOne
    @JoinColumn(name = "id_enroll", nullable = false, foreignKey = @ForeignKey(name= "FK_EnrollDetail_Enroll"))
    private Enroll enroll;
    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false, foreignKey = @ForeignKey(name= "FK_EnrollDetail_Course"))
    private Course course;
    @Column(length = 20, nullable = false)
    private String classroom;
}
