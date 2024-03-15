package br.upe.tudu.data.daos;

import br.upe.tudu.data.database.LocalStorageConnection;
import br.upe.tudu.data.models.User;
import jakarta.persistence.EntityManager;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public abstract class UserDAOImpl implements UserDAO{
    private EntityManager entityManager;

    public UserDAOImpl () {
        LocalStorageConnection.getEntityManager();

    }
    @Override
    public void save(User user) {
    }

    @Override
    public User uptade(User user) {
        return null;
    }

    @Override
    public User uptade(List<User> users) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(List<User> users) {

    }

    @Override
    public User findById(User user) {
        return null;
    }

    @Override
    public User findAll(List<User> users) {
        return null;
    }

    public  User findByIdNoTask(Long id){
        return null;
    }
}
