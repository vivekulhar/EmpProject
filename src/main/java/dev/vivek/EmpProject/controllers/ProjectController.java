package dev.vivek.EmpProject.controllers;

import dev.vivek.EmpProject.dtos.CreateProjectForEmployee;
import dev.vivek.EmpProject.dtos.CreateProjectRequestDto;
import dev.vivek.EmpProject.dtos.ProjectDto;
import dev.vivek.EmpProject.exceptions.ProjectNotFoundException;
import dev.vivek.EmpProject.models.Employee;
import dev.vivek.EmpProject.models.Project;
import dev.vivek.EmpProject.repositories.EmployeeRepository;
import dev.vivek.EmpProject.repositories.ProjectRepository;
import dev.vivek.EmpProject.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping("/createProject")
    public ResponseEntity<ProjectDto> createProject(@RequestBody CreateProjectRequestDto requestDto) {
        ProjectDto project = projectService.createProject(requestDto);

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping("/createProjectForEmployees")
    public ResponseEntity<Project> createProjectForEmployee(@RequestBody CreateProjectForEmployee requestDto) {
        Project project = projectService.createProjectForEmployee(requestDto);

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping("/assignProjectToEmployees/{projId}/{empId}")
    public ResponseEntity<ProjectDto> assignProjectToEmployees(@PathVariable(name = "projId") Integer projId,
                                           @PathVariable(name = "empId") Integer empId) throws ProjectNotFoundException {
        ProjectDto project = projectService.assignProjectToEmployees(projId, empId);

        return new ResponseEntity<>(project,HttpStatus.OK);
    }

    @GetMapping(value = "/getProject/{projId}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable(name = "projId") Integer projId) throws ProjectNotFoundException {
        ProjectDto project = projectService.getProject(projId);

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllProjects")
    public ResponseEntity<List<ProjectDto>> getAllProjects(){
        List<ProjectDto> projects = projectService.getAllProjects();

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}