package ru.alexk.project.DAO;

import ru.alexk.project.entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class UserDAO {
    private final EntityManager manager;
    public UserDAO(EntityManager manager) {this.manager = manager;}

    public User findById(long id){
        User foundUser = manager.find(User.class,id);
        if (foundUser == null){
            throw new EntityNotFoundException(
                    "Can't find a User for ID " + id
            );
        }
        return foundUser;
    }

    public User findByNickname(String nick){
        return manager.createQuery(
                "from User where nickname = :n",User.class
        ).setParameter("n", nick).getSingleResult();
    }

    public void create(User user){
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();
    }

    public void delete(User user){
        User foundUser = manager.find(User.class,user.getId());
        manager.getTransaction().begin();
        manager.remove(foundUser);
        manager.getTransaction().commit();
    }

    public void update(User user){
        manager.getTransaction().begin();
        manager.merge(user);
        manager.getTransaction().commit();
    }
}
