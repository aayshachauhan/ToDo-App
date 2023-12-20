package com.ayesha.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping({"/", "viewToDoList"})
	public String viewAllToDoItems(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("list", todoService.getAllToDoItems());
		model.addAttribute("message", message);
		
		return "ViewToDoList";
	}
	
	@GetMapping("/updateToDoStatus/{id}")
	public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if (todoService.updateStatus(id)) {
			redirectAttributes.addFlashAttribute("message", "Update Successfully");
			
			return "redirect:/viewToDoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Fail to Update");
		return "redirect:/viewToDoList";
	}

	@GetMapping("/addToDoItem")
	public String addToDoItem(Model model) {
		model.addAttribute("todo", new ToDo());
		
		return "AddToDoItem";
	}

	@PostMapping("/saveToDoItem")
	public String saveToDoItem(ToDo todo, RedirectAttributes redirectAttributes) {
		if(todoService.saveOrUpdateToDoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Save Success");
			return "redirect:/viewToDoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Save Failure");
		return "redirect:/addToDoItem";
	}
	
	@GetMapping("/editToDoItem/{id}")
	public String editToDoItem(@PathVariable Long id, Model model) {
		model.addAttribute("todo", todoService.getToDoItemById(id));
		
		return "EditToDoItem";
	}

	@PostMapping("/editSaveToDoItem")
	public String editSaveToDoItem(ToDo todo, RedirectAttributes redirectAttributes) {
		if(todoService.saveOrUpdateToDoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Edit Success");
			return "redirect:/viewToDoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Edit Failure");
		return "redirect:/editToDoItem/" + todo.getId();	
	}
	
	@GetMapping("/deleteToDoItem/{id}")
	public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if (todoService.deleteToDoItem(id)) {
			redirectAttributes.addFlashAttribute("message", "Delete Success");
			return "redirect:/viewToDoList";
		}
		
		redirectAttributes.addFlashAttribute("message", "Delete Failure");
		return "redirect:/viewToDoList";
	}
}
