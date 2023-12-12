package com.ayesha.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ayesha.todo.model.ToDo;

@Service
public interface TodoService {

	List<ToDo> getAllToDoItems();
	
	ToDo getToDoItemById(Long id);
	
	boolean updateStatus(Long id);
	
	boolean saveOrUpdateToDoItem(ToDo todo);
	
	boolean deleteToDoItem(Long id);
}
