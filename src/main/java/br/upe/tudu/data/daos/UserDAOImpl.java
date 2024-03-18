package br.upe.tudu.data.daos;

import br.upe.tudu.data.models.User;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDAOImpl implements UserDAO{

    // ATRIBUTOS
    private final EntityManager entityManager;

    // MÉTODOS DE ACESSO
    public UserDAOImpl (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // OPERAÇÕES DAO
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    public List<User> update(List<User> users) {
        return users.stream().map(entityManager::merge).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public void deleteById(List<Long> ids) {
        List<User> users = entityManager.createQuery("FROM User WHERE id in :ids", User.class)
                            .setParameter("ids", ids)
                            .getResultList();
        users.forEach(entityManager::remove);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public void delete(List<User> users) {
        users.stream().map(user -> entityManager.find(user.getClass(), user.getId())).forEach(entityManager::remove);
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

}
