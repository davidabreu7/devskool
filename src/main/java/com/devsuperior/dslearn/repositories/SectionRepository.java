package com.devsuperior.dslearn.repositories;

import com.devsuperior.dslearn.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {
}