package dev.vivek.EmpProject.services;

import dev.vivek.EmpProject.dtos.*;
import dev.vivek.EmpProject.exceptions.ProjectNotFoundException;
import dev.vivek.EmpProject.models.Employee;
import dev.vivek.EmpProject.models.Project;
import dev.vivek.EmpProject.repositories.EmployeeRepository;
import dev.vivek.EmpProject.repositories.ProjectRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private EmployeeRepository employeeRepository;
    private MappingClass mappingClass = new MappingClass();
    public ProjectService(ProjectRepository projectRepository, EmployeeRepository employeeRepository){
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }
    public ProjectDto createProject(CreateProjectRequestDto requestDto) {
        System.out.println("\nCreate a new Project.\n");

        // new Project
        Project project = new Project(requestDto.getProjectName(), requestDto.getTechnologyUsed());

        // save Project
        project = projectRepository.save(project);
        System.out.println("\nSaved Project :: " + project + "\n");
        return mappingClass.mapProjectToDTO(project);
    }

    public Project createProjectForEmployee(CreateProjectForEmployee requestDto) {
        System.out.println("\nCreate new Project and add existing Employees into this Project." + "\n");

        // create Employee set
        Set<Employee> employees = new HashSet<>();

        // get first Employee
        for (Integer empid : requestDto.getEmpId()){
            int emplId = empid;
            Employee employee1 = this.employeeRepository.getReferenceById(emplId);
            System.out.println("\nEmployee details :: " + employee1.toString() + "\n");

            employees.add(employee1);
        }

        // new Project
        Project project = new Project(requestDto.getProjectName(),
                requestDto.getTechnologyUsed());

        // assign Employee Set to Project
        project.setEmployees(employees);

        // save Project
        project = projectRepository.save(project);

        ProjectDto projectDto = mappingClass.mapProjectToDTO(project);
        System.out.println("\nSaved Project :: " + project + "\n");

        return project;
    }

    public ProjectDto assignProjectToEmployees(Integer projId, Integer empId) throws ProjectNotFoundException {
        System.out.println("\nFetch existing Project and add existing Employee into this Project." + "\n");

        // get Employee
        Employee employee = this.employeeRepository.getReferenceById(empId);
        System.out.println("\nEmployee details :: " + employee.toString() + "\n");

        // new Project
        Project project = this.projectRepository.getReferenceById(projId);
        System.out.println("\nProject details :: " + project.toString() + "\n");

        // create Employee set
        Set<Employee> employees = new HashSet<>();
        employees.add(employee);

        // assign Employee Set to Project
        project.setEmployees(employees);

        // save Project
        project = projectRepository.save(project);
        System.out.println("\nSaved Project :: " + project + "\n");
        return mappingClass.mapProjectToDTO(project);
    }

//    public Project getProject(Integer projId) {
//        System.out.println("Fetch Project and its Employees." + "\n");
//
//        // get Project details
//        Project project = this.projectRepository.getReferenceById(projId);
//        System.out.println("\nProject details :: " + project.toString() + "\n");
//        System.out.println("\nEmployees details :: " + project.getEmployees() + "\n");
//
//        System.out.println("Done!!!" + "\n");
//
//        return project;
//    }
    public ProjectDto getProject(int projectId) throws ProjectNotFoundException {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            return mappingClass.mapProjectToDTO(optionalProject.get());
        } else {
            throw new ProjectNotFoundException("Project not found with id: " + projectId);
        }
    }
    private EmployeeDTO mapEmployeeToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        // Map employee properties to DTO
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setTechnicalSkill(employee.getTechnicalSkill());
        return employeeDTO;
    }
    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDto> projectDtos = new ArrayList<>();

        for (Project project : projects) {
            ProjectDto projectDto = mappingClass.mapProjectToDTO(project);
            projectDtos.add(projectDto);
        }

        return projectDtos;
    }


}
