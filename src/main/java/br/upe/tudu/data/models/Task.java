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
    @JoinColumn(name = "id")
    private Long id;

    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "categoria")
    private String categoria;

    @JoinColumn(name = "date")
    private LocalDate date;

    @OneToMany
    @JoinColumn(name = "task_id")
    private List<SubTasks> subTasks;

    // MÉTODOS DE ACESSO

    public Task(){}

    public Task(Long id, String name, List<SubTasks> subTasks) {
        this.id = id;
        this.name = name;
        this.subTasks = subTasks;
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
}
