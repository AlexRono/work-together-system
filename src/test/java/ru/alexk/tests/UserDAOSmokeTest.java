package ru.alexk.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import ru.alexk.project.DAO.UserDAO;
import ru.alexk.project.entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class UserDAOSmokeTest {

    private EntityManagerFactory factory;
    private EntityManager manager;
    private UserDAO userDAO;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
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
        User user = new User("SteveB", User.ProjectAccessRole.USER);
        userDAO.create(user);
        User foundUser = userDAO.findById(user.getId());
        assertEquals(user,foundUser);
    }

    @Test
    public void findByNickname() {
        User user = new User("SteveB", User.ProjectAccessRole.USER);
        userDAO.create(user);
        User foundUser = userDAO.findByNickname("SteveB");
        assertEquals(user,foundUser);
    }

    @Test
    public void create() {
        User user = new User("SteveB", User.ProjectAccessRole.USER);
        userDAO.create(user);
        assertNotNull(manager.find(User.class,user.getId()));
    }

    @Test
    public void delete() {
        User user = new User("SteveB", User.ProjectAccessRole.USER);
        userDAO.create(user);
        long userID = user.getId();
        userDAO.delete(user);
        assertEquals(null,(manager.find(User.class,userID)));
    }

    @Test
    public void update() {
        User user = new User("SteveB", User.ProjectAccessRole.USER);
        userDAO.create(user);
        long userID = user.getId();
        user.setNickname("SteveB1985");
        userDAO.update(user);
        User foundUser = userDAO.findById(userID);
        assertEquals("SteveB1985",foundUser.getNickname());
    }
}