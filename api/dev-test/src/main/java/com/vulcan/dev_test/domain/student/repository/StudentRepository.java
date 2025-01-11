package com.vulcan.dev_test.domain.student.repository;

import com.vulcan.dev_test.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface StudentRepository extends JpaRepository<Student, BigInteger> {
}
