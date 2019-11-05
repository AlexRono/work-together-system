package ru.alexk.project.entities;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    public User() {};
    @Id
    @GeneratedValue
    private long id;
    public void setId(long id) {this.id = id;}
    public long getId() {return id;}

    @Column(length = 50,unique = true,nullable = false)
    private String nickname;
    public String getNickname() {return nickname;}
    public void setNickname(String nickname) {this.nickname = nickname;}

    public enum ProjectAccessRole {ADMIN,USER,NOACCESS}

    @Column(name = "projectAccessRole", length = 20,nullable = false)
    private ProjectAccessRole accessLevel;
    public ProjectAccessRole getAccessLevel() {return accessLevel;}
    public void setAccessLevel(ProjectAccessRole accessLevel) {this.accessLevel = accessLevel;}

    public User(String nickname, ProjectAccessRole accessLevel) {
        this.nickname = nickname;
        this.accessLevel = accessLevel;
    }
}
