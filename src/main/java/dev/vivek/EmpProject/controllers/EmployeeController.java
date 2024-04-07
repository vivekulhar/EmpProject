package dev.vivek.EmpProject.controllers;

import dev.vivek.EmpProject.dtos.AssignEmployeeToProjectDto;
import dev.vivek.EmpProject.dtos.CreateEmployeeRequestDto;
import dev.vivek.EmpProject.dtos.EmployeeDTO;
import dev.vivek.EmpProject.dtos.ProjectDto;
import dev.vivek.EmpProject.services.EmployeeService;
import dev.vivek.EmpProject.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private EmployeeService employeeService;
    private ProjectService projectService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ProjectService projectService){
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @PostMapping(value = "/createEmployee")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody CreateEmployeeRequestDto requestDto) {

        EmployeeDTO employee = this.employeeService.createEmployee(requestDto);


        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping(value = "/createEmployeeForProject/{projId}")
    public ResponseEntity<ProjectDto> createEmployeeForProject(@RequestBody CreateEmployeeRequestDto requestDto,
                                           @PathVariable(name = "projId") String projId) {

        ProjectDto project = this.employeeService.createEmployeeForProject(requestDto, projId);


        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping(value = "/assignEmployeeToProject/{projId}")
    public ResponseEntity<ProjectDto> assignEmployeeToProject(@RequestBody AssignEmployeeToProjectDto requestDto, @PathVariable(name = "projId") Integer projId) {
        ProjectDto project = employeeService.assignEmployeeToProject(requestDto, projId);

        return new ResponseEntity<>(project,HttpStatus.OK);
    }

    @GetMapping(value = "/getEmployee/{empId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable(name = "empId") Integer empId) {
        EmployeeDTO employee = employeeService.getEmployee(empId);

        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
}
