package com.mitocode.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Enroll {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnroll;
    @Column(nullable = false)
    private LocalDateTime enrollDate;
    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false, foreignKey = @ForeignKey(name= "FK_Enroll_Student"))
    private Student student;
    @Column(nullable = false)
    private boolean enabled;
    @OneToMany(mappedBy = "enroll", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EnrollDetail> details;
}
