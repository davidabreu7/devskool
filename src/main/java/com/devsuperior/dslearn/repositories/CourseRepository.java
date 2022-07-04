package com.devsuperior.dslearn.repositories;

import com.devsuperior.dslearn.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}

