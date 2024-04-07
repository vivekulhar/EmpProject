package dev.vivek.EmpProject.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectDto {
    private int id;
    private String projectName;
    private String technologyUsed;
    private List<EmployeeDTO> employees;
}
