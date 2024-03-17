package br.upe.tudu.rest.controllers;

import br.upe.tudu.data.models.User;
import br.upe.tudu.rest.dtos.UserDTO;
import br.upe.tudu.rest.services.UserService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/*")
    public UserDTO getUser(@RequestParam Long id) {
        System.out.println(id);
        return new UserDTO(1L, "Luan");
    }

    @PostMapping("/")
    public void registerUser(User user) {
        userService.registerUser(user);
    }

    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }

}
