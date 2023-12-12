package com.ayesha.todo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayesha.todo.model.ToDo;
import com.ayesha.todo.repository.ToDoRepo;
import com.ayesha.todo.service.TodoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ToDoServiceImpl implements TodoService{

	@Autowired
	private ToDoRepo toDoRepo;
	
	@Override
	public List<ToDo> getAllToDoItems() {

		List<ToDo> todoList = toDoRepo.findAll();
		
		return todoList;
	}

	@Override
	public ToDo getToDoItemById(Long id) {
		
	//	Optional<ToDo> todo = toDoRepo.findById(id);    -- one more optional method also working
		return toDoRepo.findById(id).get();
	}

	@Override
	public boolean updateStatus(Long id) {

		ToDo todo = getToDoItemById(id);		
		todo.setStatus("Completed");
		
		return saveOrUpdateToDoItem(todo);
	}

	@Override
	public boolean saveOrUpdateToDoItem(ToDo todo) {

		ToDo updatedObj = toDoRepo.save(todo);
		
		if(getToDoItemById(updatedObj.getId()) !=null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteToDoItem(Long id) {
		
		toDoRepo.deleteById(id);
		
		if(getToDoItemById(id) == null) {			
			return true;
		}		
		return false;
	}

}
