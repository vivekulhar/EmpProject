package dev.vivek.EmpProject.dtos;

import dev.vivek.EmpProject.models.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreateEmployeeRequestDto {
    private int id;
    private String name;
    private String email;
    private String technicalSkill;



}
