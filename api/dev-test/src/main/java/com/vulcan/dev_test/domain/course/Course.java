package com.vulcan.dev_test.domain.course;

import com.vulcan.dev_test.domain.student.Student;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Min(1)
    private Long id;

    @NonNull
    @NotEmpty
    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "status")
    private Boolean status;

    @NotNull
    @Column(name = "places")
    private Integer places;
    @NotNull

    @Column(name = "occupied_places",nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer occupiedPlaces;

    @ManyToMany
    @JoinTable(
            name = "course_has_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students = new HashSet<>();

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
