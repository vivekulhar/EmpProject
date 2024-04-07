package dev.vivek.EmpProject.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeDTO {
    private int id;
    private String name;
    private String email;
    private String technicalSkill;
    private List<ProjectDto> projects;
    // Getters and setters
}