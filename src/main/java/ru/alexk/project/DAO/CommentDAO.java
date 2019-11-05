package ru.alexk.project.DAO;

import ru.alexk.project.entities.Comment;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class CommentDAO {
    private final EntityManager manager;
    public CommentDAO(EntityManager manager) {this.manager = manager;}

    public Comment findById(int id){
        Comment foundComment = manager.find(Comment.class,id);
        if (foundComment == null){
            throw new EntityNotFoundException(
                    "Can't find a Comment for ID " + id
            );
        }
        return foundComment;
    }

    public void create(Comment comment){
        manager.getTransaction().begin();
        manager.persist(comment);
        manager.getTransaction().commit();
    }

    public void delete(Comment comment){
        Comment foundComment = manager.find(Comment.class,comment.getId());
        manager.getTransaction().begin();
        manager.remove(foundComment);
        manager.getTransaction().commit();
    }

    public void update(Comment comment){
        manager.getTransaction().begin();
        manager.merge(comment);
        manager.getTransaction().commit();
    }
}
