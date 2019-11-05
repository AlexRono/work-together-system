package ru.alexk.project.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User author;

    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private String commentText;

    //get and set methods
    public int getId() {return id;}
    public User getAuthor() {return author;}
    public Date getCreationDate() {return creationDate;}
    public String getCommentText() {return commentText;}
    public void setId(int id) {this.id = id;}
    public void setAuthor(User author) {this.author = author;}
    public void setCreationDate(Date creationDate) {this.creationDate = creationDate;}
    public void setCommentText(String commentText) {this.commentText = commentText;}
}
