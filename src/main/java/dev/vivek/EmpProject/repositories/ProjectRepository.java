package dev.vivek.EmpProject.repositories;

import dev.vivek.EmpProject.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}