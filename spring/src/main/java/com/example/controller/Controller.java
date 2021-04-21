package com.example.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Task;
import com.example.repository.TaskRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Controller {

	@Autowired
	TaskRepository TaskRepo;
	
	@GetMapping("/getTask")
	public List<Task> getTasks(){
		return (List<Task>) TaskRepo.findAll();
	}
	
	@PostMapping("/changeStatus")
	public Optional<Task> getTask(@RequestBody String id) {
		return TaskRepo.findById(id);
	}
	
	@PostMapping("/editStatus")
	public void editStatus(@RequestBody Map<String,Object> data) {
		var task = TaskRepo.findById(data.get("id").toString());
		task.get().setStatus(data.get("status").toString());
		TaskRepo.save(task.get());
	}
	
	@PostMapping("/saveTask")
	public void saveTask(@RequestBody Map<String,Object> data) {
		Task task = new Task();
		task.setTaskName(data.get("taskName").toString());
		task.setTaskHolderName(data.get("taskHolderName").toString());
		task.setDescription(data.get("description").toString());
		task.setDueDate(data.get("dueDate").toString());
		task.setStatus("Not Started");
		TaskRepo.save(task);
	}
	
	@PostMapping("/delete")
	public void deleteTask(@RequestBody String id) {
		TaskRepo.deleteById(id);
	}
}
