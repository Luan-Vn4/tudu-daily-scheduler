package br.upe.tudu.data.daos;

import br.upe.tudu.data.models.Task;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TaskDAOImpl implements TaskDAO {

    private final EntityManager entityManager;

    public TaskDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Task task) {
        entityManager.persist(task);
    }

    @Override
    public Task update(Task task) {
        return entityManager.merge(task);
    }

    @Override
    public List<Task> update(List<Task> tasks) {
        return tasks.stream().map(entityManager::merge).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        Task task = entityManager.find(Task.class, id);
        if (task != null){
            entityManager.remove(task);
        }
    }

    @Override
    public void deleteById(List<Long> ids) {
        List<Task> tasks =  entityManager.createQuery("FROM Task WHERE id in :ids", Task.class)
                            .setParameter("ids", ids)
                            .getResultList();
        tasks.forEach(entityManager::remove);
    }

    @Override
    public void delete(Task task) {
        entityManager.remove(task);
    }

    @Override
    public void delete(List<Task> tasks) {
        tasks.stream().map(user -> entityManager.find(user.getClass(), user.getId())).forEach(entityManager::remove);
    }

    @Override
    public Task findById(Long id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public List<Task> findAll() {
        return entityManager.createQuery("FROM Task", Task.class).getResultList();
    }

    @Override
    public List<Task> findTasksByUser(Long id) {
        return entityManager.createQuery("FROM Task WHERE user.id = :id", Task.class)
                            .setParameter("id", id)
                            .getResultList();
    }

}
