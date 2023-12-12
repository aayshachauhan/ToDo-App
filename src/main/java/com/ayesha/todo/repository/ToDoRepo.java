package com.ayesha.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayesha.todo.model.ToDo;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo, Long>{

}
