package br.upe.tudu.data.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "application_users")
public class User {

    // ATRIBUTOS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id")
    private Long id;

    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "password")
    private String password;

    @OneToMany
    @JoinColumn(name = "task_id")
    List<Task> tasks;

    // MÉTODOS DE ACESSO

    public User() {}

    public User(Long id, String name, String password, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
