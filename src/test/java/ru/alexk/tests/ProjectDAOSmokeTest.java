package ru.alexk.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.alexk.project.DAO.ProjectDAO;
import ru.alexk.project.DAO.TaskDAO;
import ru.alexk.project.entities.Project;
import ru.alexk.project.entities.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class ProjectDAOSmokeTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private ProjectDAO projectDAO;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        projectDAO = new ProjectDAO(manager);
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
        Project project = new Project();
        project.setCreationDate(new Date());
        project.setDescription("Simple project description");
        project.setDueDate(new Date());
        project.setProjectName("Simple project name");
        project.setProjectStatus(Project.ProjectStatus.ACTIVE);
        ArrayList<Task> listT = new ArrayList<>();
        project.setTasks(listT);
        //-----------------------------------

        projectDAO.create(project);
        int projectID = project.getId();
        Project foundProj = projectDAO.findById(projectID);
        assertEquals(project,foundProj);
    }

    @Test
    public void create() {
        //-----------------------------------
        Project project = new Project();
        project.setCreationDate(new Date());
        project.setDescription("Simple project description");
        project.setDueDate(new Date());
        project.setProjectName("Simple project name");
        project.setProjectStatus(Project.ProjectStatus.ACTIVE);
        ArrayList<Task> listT = new ArrayList<>();
        project.setTasks(listT);
        //-----------------------------------

        projectDAO.create(project);
        int projectID = project.getId();
        assertNotNull(manager.find(Project.class,projectID));
    }

    @Test
    public void delete() {
        //-----------------------------------
        Project project = new Project();
        project.setCreationDate(new Date());
        project.setDescription("Simple project description");
        project.setDueDate(new Date());
        project.setProjectName("Simple project name");
        project.setProjectStatus(Project.ProjectStatus.ACTIVE);
        ArrayList<Task> listT = new ArrayList<>();
        project.setTasks(listT);
        //-----------------------------------

        projectDAO.create(project);
        int projectID = project.getId();
        projectDAO.delete(project);
        assertNull(manager.find(Project.class,projectID));
    }

    @Test
    public void update() {
        //-----------------------------------
        Project project = new Project();
        project.setCreationDate(new Date());
        project.setDescription("Simple project description");
        project.setDueDate(new Date());
        project.setProjectName("Simple project name");
        project.setProjectStatus(Project.ProjectStatus.ACTIVE);
        ArrayList<Task> listT = new ArrayList<>();
        project.setTasks(listT);
        //-----------------------------------

        projectDAO.create(project);
        int projectID = project.getId();
        project.setDescription("Simple project description UPDATED");
        projectDAO.update(project);
        Project foundProj = projectDAO.findById(projectID);
        assertEquals("Simple project description UPDATED", foundProj.getDescription());
    }
}