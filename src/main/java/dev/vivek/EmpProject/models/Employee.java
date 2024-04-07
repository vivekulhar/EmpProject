package dev.vivek.EmpProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "technicalSkill")
    private String technicalSkill;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "EMPLOYEE_PROJECT_MAPPING",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects;

    public Employee() {
    }

    public Employee(String name, String email, String technicalSkill) {
        this.name = name;
        this.email = email;
        this.technicalSkill = technicalSkill;
    }

    @Override
    public String toString() {
        return "Employee [email=" + email + ", id=" + id + ", name=" + name + ", technicalSkill=" + technicalSkill + "]";
    }
}