package br.upe.tudu.rest.controllers;

import br.upe.tudu.data.models.User;
import br.upe.tudu.rest.dtos.UserDTO;
import br.upe.tudu.rest.services.UserService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
public class UserController {

    // ATRIBUTOS
    UserService userService;

    // MÉTODOS DE ACESSO
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // MÉTODOS HTTP
    @PostMapping("/")
    public void registerUser(@RequestBody User user) {
        userService.registerUser(user);
    }

    @GetMapping("/")
    public UserDTO getUser(@RequestParam Long id) {
        return userService.findUser(id);
    }

    @PutMapping("/")
    public UserDTO updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }

}
