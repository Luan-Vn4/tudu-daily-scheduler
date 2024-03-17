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
        return userService.findUser(id);
    }

    @PostMapping("/")
    public void registerUser(@RequestBody User user) {
        userService.registerUser(user);
    }

    @PutMapping("/")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/*")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }

}
