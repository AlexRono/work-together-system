package ru.alexk.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.alexk.project.DAO.CommentDAO;
import ru.alexk.project.DAO.ProjectDAO;
import ru.alexk.project.DAO.UserDAO;
import ru.alexk.project.entities.Comment;
import ru.alexk.project.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Date;

import static org.junit.Assert.*;

public class CommentDAOSmokeTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private CommentDAO commentDAO;
    private UserDAO userDAO;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        commentDAO = new CommentDAO(manager);
        userDAO = new UserDAO(manager);
    }

    @After
    public void cleanup() {
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void findById() {
        //---------------------------
        Comment comment = new Comment();
        User user = new User("SteveB", User.ProjectAccessRole.USER);
        user.setPassword("password");
        userDAO.create(user);
        comment.setAuthor(user);
        comment.setCommentText("Simple comment text");
        comment.setCreationDate(new Date());
        //---------------------------

        commentDAO.create(comment);
        int commentID = comment.getId();
        Comment foundC = commentDAO.findById(commentID);
        assertEquals(comment,foundC);
    }

    @Test
    public void create() {
        //---------------------------
        Comment comment = new Comment();
        User user = new User("SteveB", User.ProjectAccessRole.USER);
        user.setPassword("password");
        userDAO.create(user);
        comment.setAuthor(user);
        comment.setCommentText("Simple comment text");
        comment.setCreationDate(new Date());
        //---------------------------

        commentDAO.create(comment);
        int commentID = comment.getId();
        assertNotNull(manager.find(Comment.class,commentID));
    }

    @Test
    public void delete() {
        //---------------------------
        Comment comment = new Comment();
        User user = new User("SteveB", User.ProjectAccessRole.USER);
        user.setPassword("password");
        userDAO.create(user);
        comment.setAuthor(user);
        comment.setCommentText("Simple comment text");
        comment.setCreationDate(new Date());
        //---------------------------

        commentDAO.create(comment);
        int commentID = comment.getId();
        commentDAO.delete(comment);
        assertNull(manager.find(Comment.class,commentID));

    }

    @Test
    public void update() {
        //---------------------------
        Comment comment = new Comment();
        User user = new User("SteveB", User.ProjectAccessRole.USER);
        user.setPassword("password");
        userDAO.create(user);
        comment.setAuthor(user);
        comment.setCommentText("Simple comment text");
        comment.setCreationDate(new Date());
        //---------------------------

        commentDAO.create(comment);
        int commentID = comment.getId();
        comment.setCommentText("Simple comment text UPDATED");
        commentDAO.update(comment);
        Comment foundC = commentDAO.findById(commentID);
        assertEquals("Simple comment text UPDATED", foundC.getCommentText());
    }
}