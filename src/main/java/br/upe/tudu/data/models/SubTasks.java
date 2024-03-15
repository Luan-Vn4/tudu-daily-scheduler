package br.upe.tudu.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "sub_tasks")
public class SubTasks {

    @Id
    @JoinColumn(name = "id")
    private Long id;

    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "is_complete")
    private boolean isComplete;

    public SubTasks() {}

    public SubTasks(Long id, String name) {
        this.id = id;
        this.name = name;
        this.isComplete = false;
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

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

}
