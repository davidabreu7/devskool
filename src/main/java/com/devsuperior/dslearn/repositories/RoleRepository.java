package com.devsuperior.dslearn.repositories;

import com.devsuperior.dslearn.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findBy();
}