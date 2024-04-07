package dev.vivek.EmpProject.services;

import dev.vivek.EmpProject.dtos.CreateEmployeeRequestDto;
import dev.vivek.EmpProject.dtos.EmployeeDTO;
import dev.vivek.EmpProject.dtos.MappingClass;
import dev.vivek.EmpProject.dtos.ProjectDto;
import dev.vivek.EmpProject.models.Employee;
import dev.vivek.EmpProject.models.Project;
import dev.vivek.EmpProject.repositories.EmployeeRepository;
import dev.vivek.EmpProject.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;
    private MappingClass mappingClass = new MappingClass();
    public EmployeeService(EmployeeRepository employeeRepository, ProjectRepository projectRepository){
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }
    public EmployeeDTO createEmployee(CreateEmployeeRequestDto requestDto) {

        System.out.println("\nCreate a new Employee." + "\n");

        // create a new Employee
        Employee employee = new Employee(requestDto.getName(), requestDto.getEmail(),
                requestDto.getTechnicalSkill());

        // save Employee
        employee = employeeRepository.save(employee);
        System.out.println("\nSaved employee :: " + employee + "\n");
        EmployeeDTO employeeDTO = mappingClass.mapEmployeeToDTO(employee);
        return employeeDTO;
    }

    public ProjectDto createEmployeeForProject(CreateEmployeeRequestDto requestDto, String projId) {

        System.out.println("\nCreate a new Employee and assign to an existing Project." + "\n");

        // create a new Employee
        Employee employee = new Employee(requestDto.getName(), requestDto.getEmail(),
                requestDto.getTechnicalSkill());

        // save Employee
        employee = employeeRepository.save(employee);
        System.out.println("\nSaved employee :: " + employee + "\n");

        // get a Project
        Project project = this.projectRepository.getReferenceById(Integer.valueOf(projId));
        System.out.println("\nProject details :: " + project.toString() + "\n");

        // create Employee set
        Set<Employee> employees = new HashSet<>();
        employees.add(employee);

        // assign Employee Set to Project
        project.setEmployees(employees);

        // save Project
        project = projectRepository.save(project);
        ProjectDto projectDto = mappingClass.mapProjectToDTO(project);
        System.out.println("\nEmployee assigned to the Project." + "\n");

        return projectDto;
    }

    public ProjectDto assignEmployeeToProject(Integer projId) {
        System.out.println("\nFetch existing Employee details and assign them to an existing Project." + "\n");

        // get first Employee
        int emplId = 1;
        Employee employee1 = this.employeeRepository.getReferenceById(emplId);
        System.out.println("\nEmployee details :: " + employee1.toString() + "\n");

        // get first Employee
        emplId = 8;
        Employee employee2 = this.employeeRepository.getReferenceById(emplId);
        System.out.println("\nEmployee details :: " + employee2.toString() + "\n");

        // get a Project
        Project project = this.projectRepository.getReferenceById(projId);
        System.out.println("\nProject details :: " + project.toString() + "\n");

        // create Employee set
        Set<Employee> employees = new HashSet<>();
        employees.add(employee1);
        employees.add(employee2);

        // assign Employee Set to Project
        project.setEmployees(employees);

        // save Project
        project = projectRepository.save(project);
        ProjectDto projectDto = mappingClass.mapProjectToDTO(project);
        System.out.println("Employees assigned to the Project." + "\n");

        return projectDto;
    }

    public EmployeeDTO getEmployee(Integer empId) {

        System.out.println("Fetch Employee and Project details.");

        // get Employee details
        Employee employee = this.employeeRepository.getReferenceById(empId);
        System.out.println("\nEmployee details :: " + employee.toString() + "\n");
        System.out.println("\nProject details :: " + employee.getProjects() + "\n");
        employee.setProjects(employee.getProjects());
        System.out.println("Done!!!" + "\n");

        return mappingClass.mapEmployeeToDTO(employee);
    }
}
