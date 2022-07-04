package com.devsuperior.dslearn.repositories;

import com.devsuperior.dslearn.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}