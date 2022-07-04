package com.devsuperior.dslearn.repositories;

import com.devsuperior.dslearn.entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}

