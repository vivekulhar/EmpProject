package dev.vivek.EmpProject.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssignEmployeeToProjectDto {
    private List<Integer> empId;
}
