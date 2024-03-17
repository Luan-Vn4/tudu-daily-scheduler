package br.upe.tudu.rest.services;

import br.upe.tudu.data.daos.UserDAO;
import br.upe.tudu.data.models.User;
import br.upe.tudu.rest.dtos.UserDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public void registerUser(User user) {
        this.userDAO.save(user);
    }

    @Transactional
    public User updateUser(User user) {
        return this.userDAO.update(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        this.userDAO.deleteById(id);
    }

    public UserDTO findUser(Long id) {
        return UserDTO.from(this.userDAO.findById(id));
    }

}
