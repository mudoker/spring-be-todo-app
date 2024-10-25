package org.mudoker.repositories;

import lombok.AllArgsConstructor;
import org.mudoker.global.Priority;
import org.mudoker.models.TaskModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TaskRepository {
	JdbcTemplate jdbcTemplate;

	public List <TaskModel> getAllTasks () {
		String sql = "select * from tasks";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
					return new TaskModel(rs.getInt("id"), rs.getString("title"), rs.getBoolean("completed"), Priority.valueOf(rs.getString("priority").toUpperCase()));
				}
		);
	}

	public void addTask(TaskModel task) {
		String sql = "INSERT INTO tasks (id, title, completed, priority) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, task.getId(), task.getTitle(), task.isCompleted(), task.getPriority().name());
	}

	public void updateTask(int id, TaskModel task) {
		String sql = "UPDATE tasks SET title = ?, completed = ?, priority = ? WHERE id = ?";
		jdbcTemplate.update(sql, task.getTitle(), task.isCompleted(), task.getPriority().name(), id);
	}

	public void deleteTask(int id) {
		String sql = "delete from tasks where id = ?";
		jdbcTemplate.update(sql, id);
	}
}
