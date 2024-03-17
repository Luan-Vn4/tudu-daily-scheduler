package br.upe.tudu.rest.dtos;

import br.upe.tudu.data.models.User;

public record UserDTO (Long id, String name) {

    public static UserDTO from(User user) {
        return new UserDTO(user.getId(), user.getName());
    }

}
