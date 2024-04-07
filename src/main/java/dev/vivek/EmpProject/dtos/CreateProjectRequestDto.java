package dev.vivek.EmpProject.dtos;

import dev.vivek.EmpProject.models.Employee;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreateProjectRequestDto {

    private int id;
    private String projectName;
    private String technologyUsed;


}
