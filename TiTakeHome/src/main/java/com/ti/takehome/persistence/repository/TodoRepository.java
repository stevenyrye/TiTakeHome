package com.ti.takehome.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ti.takehome.persistence.entity.Todo;

import jakarta.transaction.Transactional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Todo t SET t.description = :description, t.completed = :completed WHERE t.id = :id")
    int updateTodo(@Param("id") Long id,
                   @Param("description") String description,
                   @Param("completed") boolean completed);

    @Modifying
    @Transactional
    @Query("DELETE FROM Todo t WHERE t.id = :id")
    int deleteTodo(@Param("id") Long id);

}