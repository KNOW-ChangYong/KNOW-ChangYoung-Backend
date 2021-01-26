package com.example.check.entity.student;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, String> {
    Optional<Student> findById(String id);
}
