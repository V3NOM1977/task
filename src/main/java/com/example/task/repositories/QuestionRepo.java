package com.example.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.task.models.Question;

// This is a Spring Data JPA repository interface for the `Question` entity. It extends the
// `JpaRepository` interface which provides CRUD (Create, Read, Update, Delete) operations for the
// `Question` entity. The `@Repository` annotation is used to indicate that this interface is a
// repository component in the Spring framework.
@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {
}
