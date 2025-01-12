package com.vulcan.dev_test.domain.student;

import com.vulcan.dev_test.domain.course.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Min(1)
    private Long id;

    @NonNull
    @NotEmpty
    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @NotEmpty
    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(name = "gender")
    private Gender gender;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
