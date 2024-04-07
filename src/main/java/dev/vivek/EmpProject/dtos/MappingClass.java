package dev.vivek.EmpProject.dtos;

import dev.vivek.EmpProject.models.Employee;
import dev.vivek.EmpProject.models.Project;

import java.util.List;
import java.util.stream.Collectors;

public class MappingClass {
    public EmployeeDTO mapEmployeeToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        // Map employee properties to DTO
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setTechnicalSkill(employee.getTechnicalSkill());
        return employeeDTO;
    }

    public ProjectDto mapProjectToDTO(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setProjectName(project.getProjectName());
        projectDto.setTechnologyUsed(project.getTechnologyUsed());
        // Since we are not loading employees here, we can set employees to null or an empty list
        List<EmployeeDTO> employeeDTOs = project.getEmployees().stream()
                .map(this::mapEmployeeToDTO)
                .collect(Collectors.toList());
        projectDto.setEmployees(employeeDTOs); // or set to null
        return projectDto;
    }
}
