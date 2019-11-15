package ru.alexk.project.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {
    public Task() {}

    @Id
    @GeneratedValue
    private int id;

    //people
    @ManyToOne(optional = false)
    private User creator;

    @ManyToOne
    private User assignee;

    //dates
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date resolutionDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastCommentedDate;

    //details
    @Column(nullable = false)
    private String name;

    public enum TaskPriority{TRIVIAL,MINOR,MAJOR,CRITICAL}

    @Column(length = 15,nullable = false)
    @Enumerated
    private TaskPriority taskPriority;

    public enum TaskStatus{OPEN,INPROGRESS,DONE}

    @Column(length = 50,nullable = false)
    @Enumerated
    private TaskStatus taskStatus;

    @Column(nullable = false)
    private String description;

    //foreign keys
    @ManyToOne
    private Project project;

    @OneToMany
    private List<Comment> comments;

    //get and set methods

    public int getId() {return id;}
    public User getCreator() {return creator;}
    public User getAssignee() {return assignee;}
    public Date getCreationDate() {return creationDate;}
    public Date getDueDate() {return dueDate;}
    public Date getUpdateDate() {return updateDate;}
    public Date getResolutionDate() {return resolutionDate;}
    public Date getLastCommentedDate() {return lastCommentedDate;}
    public String getName() {return name; }
    public TaskPriority getTaskPriority() {return taskPriority;}
    public TaskStatus getTaskStatus() {return taskStatus;}
    public String getDescription() {return description;}
    public Project getProject() {return project;}
    public List<Comment> getComments() {return comments;}
    public void setId(int id) {this.id = id;}
    public void setCreator(User creator) {this.creator = creator;}
    public void setAssignee(User assignee) {this.assignee = assignee;}
    public void setCreationDate(Date creationDate) {this.creationDate = creationDate;}
    public void setDueDate(Date dueDate) {this.dueDate = dueDate;}
    public void setUpdateDate(Date updateDate) {this.updateDate = updateDate;}
    public void setResolutionDate(Date resolutionDate) {this.resolutionDate = resolutionDate;}
    public void setLastCommentedDate(Date lastCommentedDate) {this.lastCommentedDate = lastCommentedDate;}
    public void setName(String name) {this.name = name; }
    public void setTaskPriority(TaskPriority taskPriority) {this.taskPriority = taskPriority;}
    public void setTaskStatus(TaskStatus taskStatus) {this.taskStatus = taskStatus;}
    public void setDescription(String description) {this.description = description;}
    public void setProject(Project project) {this.project = project;}
    public void setComments(List<Comment> comments) {this.comments = comments;}


}