package com.gbs.software.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gbs.software.model.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
}
