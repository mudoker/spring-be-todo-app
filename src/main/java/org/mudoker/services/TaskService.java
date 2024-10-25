package org.mudoker.services;

import lombok.AllArgsConstructor;
import org.mudoker.models.TaskModel;
import org.mudoker.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
	private final TaskRepository taskRepository;

	public List<TaskModel> getAllTasks() {
		return taskRepository.getAllTasks();
	}

	public void addTask(TaskModel task) {
		taskRepository.addTask(task);
	}

	public void updateTask(int id, TaskModel task) {
		taskRepository.updateTask(id, task);
	}

	public void deleteTask(int id) {
		taskRepository.deleteTask(id);
	}
}

