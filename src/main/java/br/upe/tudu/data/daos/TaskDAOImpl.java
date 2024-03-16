package br.upe.tudu.data.daos;

import br.upe.tudu.data.database.LocalStorageConnection;
import br.upe.tudu.data.models.Task;
import br.upe.tudu.data.models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {

    private EntityManager entityManager;

    public TaskDAOImpl() {
        this.entityManager = LocalStorageConnection.getEntityManager();
    }

    @Override
    public void save(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
    }

    @Override
    public User uptade(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        user = entityManager.merge(user);
        transaction.commit();
        return user;
    }

    @Override
    public User uptade(List<User> users) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<User> userList = new ArrayList<>();

        for (User user : users){
            User updatedUser = entityManager.merge(user);
            userList.add(updatedUser);
        }

        transaction.commit();
        return (User) userList;
    }

    @Override
    public void deleteById(Long aLong) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User user;
        user = entityManager.find(User.class, aLong);
        if (user != null){
            entityManager.remove(user);
        }
        transaction.commit();
    }

    @Override
    public void delete(List<User> users) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        for (User user : users){
            User managedUser = entityManager.find(User.class, user.getId());
            if (managedUser != null){
                entityManager.remove(managedUser);
            }
        }
        transaction.commit();
    }

    @Override
    public User findById(User user) {
        return entityManager.find(User.class, user);
    }

    @Override
    public User findAll(List<User> users) {
        return entityManager.find(User.class, users);
    }

    @Override
    public List<Task> findTasksByUser(Long id) {
        return null;
    }
}
