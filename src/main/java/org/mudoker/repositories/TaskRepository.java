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
					return new TaskModel(rs.getInt("id"), rs.getString("title"), rs.getBoolean("isCompleted"), Priority.valueOf(rs.getString("priority").toUpperCase()));
				}
		);
	}

	public int addTask (TaskModel task) {
		String sql = "insert into tasks (title, isCompleted, priority) values (?, ?, ?)";
		return jdbcTemplate.update(sql, task.getTitle(), task.isCompleted(), task.getPriority().name());
	}

	public int updateTask(int id, TaskModel task) {
		String sql = "UPDATE tasks SET title = ?, isCompleted = ?, priority = ? WHERE id = ?";
		return jdbcTemplate.update(sql, task.getTitle(), task.isCompleted(), task.getPriority().name(), id);
	}

	public int deleteTask(int id) {
		String sql = "delete from tasks where id = ?";
		return jdbcTemplate.update(sql, id);
	}
}
