package ru.alexk.project.DAO;

import org.springframework.stereotype.Repository;
import ru.alexk.project.entities.Task;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

@Repository
public class TaskDAO {
    private final EntityManager manager;
    public TaskDAO(EntityManager manager) {this.manager = manager;}

    public Task findById(int id){
        Task foundTask = manager.find(Task.class,id);
        if (foundTask == null){
            throw new EntityNotFoundException(
                    "Can't find a Task for ID " + id
            );
        }
        return foundTask;
    }

    public void create(Task task){
        manager.getTransaction().begin();
        manager.persist(task);
        manager.getTransaction().commit();
    }

    public void delete(Task task){
        Task foundTask = manager.find(Task.class,task.getId());
        manager.getTransaction().begin();
        manager.remove(foundTask);
        manager.getTransaction().commit();
    }

    public void update(Task task){
        manager.getTransaction().begin();
        manager.merge(task);
        manager.getTransaction().commit();
    }

}