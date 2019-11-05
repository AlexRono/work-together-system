package ru.alexk.project.DAO;

import ru.alexk.project.entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class ProjectDAO {
    private final EntityManager manager;
    public ProjectDAO(EntityManager manager) {this.manager = manager;}

    public Project findById(int id){
        Project foundProject = manager.find(Project.class,id);
        if (foundProject == null){
            throw new EntityNotFoundException(
                    "Can't find a Project for ID " + id
            );
        }
        return foundProject;
    }

    public void create(Project project){
        manager.getTransaction().begin();
        manager.persist(project);
        manager.getTransaction().commit();
    }

    public void delete(Project project){
        Project foundProject = manager.find(Project.class,project.getId());
        manager.getTransaction().begin();
        manager.remove(foundProject);
        manager.getTransaction().commit();
    }

    public void update(Project project){
        manager.getTransaction().begin();
        manager.merge(project);
        manager.getTransaction().commit();
    }

}
