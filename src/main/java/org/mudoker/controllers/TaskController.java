package org.mudoker.controllers;

import lombok.AllArgsConstructor;
import org.mudoker.models.TaskModel;
import org.mudoker.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TaskController {
	private final TaskService taskService;

	@GetMapping("/tasks")
	public ResponseEntity<List<TaskModel>> getAllTasks() {
		List<TaskModel> tasks = taskService.getAllTasks();
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}

	@PostMapping("/add-task")
	public ResponseEntity<Void> addTask(@RequestBody TaskModel task) {
		taskService.addTask(task);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/update-task")
	public ResponseEntity<Void> updateTask(@RequestParam("id") int id, @RequestBody TaskModel task) {
		taskService.updateTask(id, task);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete-task")
	public ResponseEntity<Void> deleteTask(@RequestParam("id") int id) {
		taskService.deleteTask(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

