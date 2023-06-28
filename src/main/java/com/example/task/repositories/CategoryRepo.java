package com.example.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.task.models.Category;

// This code defines a repository interface for the `Category` model using Spring Data JPA. The
// `@Repository` annotation indicates that this interface is a Spring-managed repository component. The
// `JpaRepository` interface provides methods for performing CRUD (Create, Read, Update, Delete)
// operations on the `Category` entity, including pagination and sorting. The `Category` and `Long`
// parameters specify the entity type and the type of its primary key, respectively.
@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}
