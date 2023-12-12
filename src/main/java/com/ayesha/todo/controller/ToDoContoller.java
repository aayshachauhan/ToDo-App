package com.ayesha.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayesha.todo.model.ToDo;
import com.ayesha.todo.service.TodoService;

@RestController
public class ToDoContoller {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/get/allitems")
	public List<ToDo> viewAllToDoItems() {
		
		return todoService.getAllToDoItems();
		
	}

}
