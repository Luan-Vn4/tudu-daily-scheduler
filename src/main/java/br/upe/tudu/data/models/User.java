package br.upe.tudu.data.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "application_users")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@ToString
public class User {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

}
