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

	public int addTask(TaskModel task) {
		return taskRepository.addTask(task);
	}

	public int updateTask(int id, TaskModel task) {
		return taskRepository.updateTask(id, task);
	}

	public int deleteTask(int id) {
		return taskRepository.deleteTask(id);
	}
}

