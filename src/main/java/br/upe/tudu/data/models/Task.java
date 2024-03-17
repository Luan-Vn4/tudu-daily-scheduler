package br.upe.tudu.data.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "categoria")
    private String categoria;


    @Column(name = "date")
    private LocalDate date;

    @OneToMany
    @JoinColumn(name = "task_id",
                foreignKey = @ForeignKey(name="sub_tasks_tasks_fkey"))
    private List<SubTasks> subTasks;

    @ManyToOne
    @JoinColumn(name = "user_id",
                foreignKey = @ForeignKey(name="tasks_users_fkey"))
    private User user;

    // MÉTODOS DE ACESSO

    public Task(){}

    public Task(Long id, String name, List<SubTasks> subTasks, User user) {
        this.id = id;
        this.name = name;
        this.subTasks = subTasks;
        this.user = user;
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

    public List<SubTasks> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTasks> subTasks) {
        this.subTasks = subTasks;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
