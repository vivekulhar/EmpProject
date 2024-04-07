package dev.vivek.EmpProject.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PROJECT")
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "projectName")
    private String projectName;

    @Column(name = "technologyUsed")
    private String technologyUsed;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "EMPLOYEE_PROJECT_MAPPING",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> employees;

    public Project() {}


    public Project(String projectName, String technologyUsed) {
        this.projectName = projectName;
        this.technologyUsed = technologyUsed;
        this.employees = new HashSet<>(); // Initialize the set
    }
    @Override
    public String toString() {
        return "Project [id=" + id + ", projectName=" + projectName
                + ", technologyUsed=" + technologyUsed + "]";
    }
}