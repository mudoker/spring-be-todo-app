package org.mudoker.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mudoker.global.Priority;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks") // Optionally specify the table name
public class TaskModel {

	@Id
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	@Column(nullable = false)
	private boolean completed;

	@Column(nullable = false)
	private Priority priority;
}
