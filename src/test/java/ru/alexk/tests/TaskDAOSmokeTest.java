package ru.alexk.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.alexk.project.DAO.TaskDAO;
import ru.alexk.project.DAO.UserDAO;
import ru.alexk.project.entities.Comment;
import ru.alexk.project.entities.Project;
import ru.alexk.project.entities.Task;
import ru.alexk.project.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TaskDAOSmokeTest {

    private EntityManagerFactory factory;
    private EntityManager manager;
    private TaskDAO taskDAO;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        taskDAO = new TaskDAO(manager);
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
        //-----------------------------------
        User user = new User("Kevin", User.ProjectAccessRole.USER);
        Project project = new Project();
        project.setCreationDate(new Date());
        project.setDescription("Simple project description");
        project.setDueDate(new Date());
        project.setProjectName("Simple project name");
        project.setProjectStatus(Project.ProjectStatus.ACTIVE);
        ArrayList<Task> listT = new ArrayList<>();
        project.setTasks(listT);
        Comment comment1 = new Comment();
        comment1.setAuthor(user);
        comment1.setCommentText("Simple comment text");
        comment1.setCreationDate(new Date());
        Comment comment2 = new Comment();
        comment2.setAuthor(user);
        comment2.setCommentText("Simple comment text");
        comment2.setCreationDate(new Date());
        manager.getTransaction().begin();
        manager.persist(user);
        manager.persist(project);
        manager.persist(comment1);
        manager.persist(comment2);
        manager.getTransaction().commit();

        Task task = new Task();
        task.setCreator(user);
        task.setAssignee(user);
        task.setCreationDate(new Date());
        task.setDueDate(new Date());
        task.setUpdateDate(new Date());
        task.setResolutionDate(new Date());
        task.setLastCommentedDate(new Date());
        task.setName("Simple name");
        task.setTaskPriority(Task.TaskPriority.MINOR);
        task.setTaskStatus(Task.TaskStatus.INPROGRESS);
        task.setDescription("Default description");
        task.setProject(project);
        ArrayList<Comment> listC = new ArrayList<>();
        listC.add(comment1);
        listC.add(comment2);
        task.setComments(listC);
        //-----------------------------------

        taskDAO.create(task);
        int taskID = task.getId();
        Task foundTask = taskDAO.findById(taskID);
        assertEquals(task,foundTask);
    }

    @Test
    public void create() {
        //-----------------------------------
        User user = new User("Kevin", User.ProjectAccessRole.USER);
        Project project = new Project();
        project.setCreationDate(new Date());
        project.setDescription("Simple project description");
        project.setDueDate(new Date());
        project.setProjectName("Simple project name");
        project.setProjectStatus(Project.ProjectStatus.ACTIVE);
        ArrayList<Task> listT = new ArrayList<>();
        project.setTasks(listT);
        Comment comment1 = new Comment();
        comment1.setAuthor(user);
        comment1.setCommentText("Simple comment text");
        comment1.setCreationDate(new Date());
        Comment comment2 = new Comment();
        comment2.setAuthor(user);
        comment2.setCommentText("Simple comment text");
        comment2.setCreationDate(new Date());
        manager.getTransaction().begin();
        manager.persist(user);
        manager.persist(project);
        manager.persist(comment1);
        manager.persist(comment2);
        manager.getTransaction().commit();

        Task task = new Task();
        task.setCreator(user);
        task.setAssignee(user);
        task.setCreationDate(new Date());
        task.setDueDate(new Date());
        task.setUpdateDate(new Date());
        task.setResolutionDate(new Date());
        task.setLastCommentedDate(new Date());
        task.setName("Simple name");
        task.setTaskPriority(Task.TaskPriority.MINOR);
        task.setTaskStatus(Task.TaskStatus.INPROGRESS);
        task.setDescription("Default description");
        task.setProject(project);
        ArrayList<Comment> listC = new ArrayList<>();
        listC.add(comment1);
        listC.add(comment2);
        task.setComments(listC);
        //-----------------------------------

        taskDAO.create(task);
        int taskID = task.getId();
        assertNotNull(manager.find(Task.class,taskID));
    }

    @Test
    public void delete() {
        //-----------------------------------
        User user = new User("Kevin", User.ProjectAccessRole.USER);
        Project project = new Project();
        project.setCreationDate(new Date());
        project.setDescription("Simple project description");
        project.setDueDate(new Date());
        project.setProjectName("Simple project name");
        project.setProjectStatus(Project.ProjectStatus.ACTIVE);
        ArrayList<Task> listT = new ArrayList<>();
        project.setTasks(listT);
        Comment comment1 = new Comment();
        comment1.setAuthor(user);
        comment1.setCommentText("Simple comment text");
        comment1.setCreationDate(new Date());
        Comment comment2 = new Comment();
        comment2.setAuthor(user);
        comment2.setCommentText("Simple comment text");
        comment2.setCreationDate(new Date());
        manager.getTransaction().begin();
        manager.persist(user);
        manager.persist(project);
        manager.persist(comment1);
        manager.persist(comment2);
        manager.getTransaction().commit();

        Task task = new Task();
        task.setCreator(user);
        task.setAssignee(user);
        task.setCreationDate(new Date());
        task.setDueDate(new Date());
        task.setUpdateDate(new Date());
        task.setResolutionDate(new Date());
        task.setLastCommentedDate(new Date());
        task.setName("Simple name");
        task.setTaskPriority(Task.TaskPriority.MINOR);
        task.setTaskStatus(Task.TaskStatus.INPROGRESS);
        task.setDescription("Default description");
        task.setProject(project);
        ArrayList<Comment> listC = new ArrayList<>();
        listC.add(comment1);
        listC.add(comment2);
        task.setComments(listC);
        //-----------------------------------

        taskDAO.create(task);
        int taskID = task.getId();
        taskDAO.delete(task);
        assertNull(manager.find(Task.class, taskID));
    }

    @Test
    public void update() {
        //-----------------------------------
        User user = new User("Kevin", User.ProjectAccessRole.USER);
        Project project = new Project();
        project.setCreationDate(new Date());
        project.setDescription("Simple project description");
        project.setDueDate(new Date());
        project.setProjectName("Simple project name");
        project.setProjectStatus(Project.ProjectStatus.ACTIVE);
        ArrayList<Task> listT = new ArrayList<>();
        project.setTasks(listT);
        Comment comment1 = new Comment();
        comment1.setAuthor(user);
        comment1.setCommentText("Simple comment text");
        comment1.setCreationDate(new Date());
        Comment comment2 = new Comment();
        comment2.setAuthor(user);
        comment2.setCommentText("Simple comment text");
        comment2.setCreationDate(new Date());
        manager.getTransaction().begin();
        manager.persist(user);
        manager.persist(project);
        manager.persist(comment1);
        manager.persist(comment2);
        manager.getTransaction().commit();

        Task task = new Task();
        task.setCreator(user);
        task.setAssignee(user);
        task.setCreationDate(new Date());
        task.setDueDate(new Date());
        task.setUpdateDate(new Date());
        task.setResolutionDate(new Date());
        task.setLastCommentedDate(new Date());
        task.setName("Simple name");
        task.setTaskPriority(Task.TaskPriority.MINOR);
        task.setTaskStatus(Task.TaskStatus.INPROGRESS);
        task.setDescription("Default description");
        task.setProject(project);
        ArrayList<Comment> listC = new ArrayList<>();
        listC.add(comment1);
        listC.add(comment2);
        task.setComments(listC);
        //-----------------------------------

        taskDAO.create(task);
        int taskID = task.getId();
        task.setName("Testing task update");
        taskDAO.update(task);
        Task foundTask = taskDAO.findById(taskID);
        assertEquals("Testing task update",foundTask.getName());
    }
}