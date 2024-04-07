package dev.vivek.EmpProject.repositories;

import dev.vivek.EmpProject.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}