package ru.alexk.project.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue
    private int id;

    //project details
    @Column(length = 100,unique = true,nullable = false)
    private String projectName;

    @Column(nullable = false)
    private String description;

    public enum ProjectStatus {ACTIVE,PAUSED,CLOSED;}
    @Column(length = 10, nullable = false)
    private ProjectStatus projectStatus;

    //dates
    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private Date dueDate;

    //foreign keys
    @OneToMany
    private List<Task> tasks;

    //get and set methods

    public int getId() {return id;}
    public String getProjectName() {return projectName;}
    public String getDescription() {return description;}
    public ProjectStatus getProjectStatus() {return projectStatus;}
    public Date getCreationDate() {return creationDate;}
    public Date getDueDate() {return dueDate;}
    public List<Task> getTasks() {return tasks;}
    public void setId(int id) {this.id = id;}
    public void setProjectName(String projectName) {this.projectName = projectName;}
    public void setDescription(String description) {this.description = description;}
    public void setProjectStatus(ProjectStatus projectStatus) {this.projectStatus = projectStatus;}
    public void setCreationDate(Date creationDate) {this.creationDate = creationDate;}
    public void setDueDate(Date dueDate) {this.dueDate = dueDate;}
    public void setTasks(List<Task> tasks) {this.tasks = tasks;}
}
