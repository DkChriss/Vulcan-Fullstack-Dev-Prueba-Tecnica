package com.vulcan.dev_test.domain.student;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @NonNull
    @NotEmpty
    @Column(columnDefinition = "first_name")
    private String firstName;

    @NonNull
    @NotEmpty
    @Column(columnDefinition = "last_name")
    private String lastName;

    @NonNull
    @NotEmpty
    @Column(columnDefinition = "age")
    private Integer age;

    @NonNull
    @NotEmpty
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "gender")
    private Gender gender;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
