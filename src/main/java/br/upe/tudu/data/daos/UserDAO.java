package br.upe.tudu.data.daos;

import br.upe.tudu.data.database.DataBaseConnection;
import br.upe.tudu.data.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class UserDAO {

    EntityManager entityManager;

    public UserDAO() {
        this.entityManager = DataBaseConnection.getEntityManager();
    }

    public User findUserById(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.clear();
        return user;
    }

    public void save(User user) {
        entityManager.getTransaction().begin();

        try {
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (PersistenceException exception) {
            System.out.println("Houve um problema ao salvar o usuário: " + exception);
            entityManager.getTransaction().rollback();
        }

    }

}
