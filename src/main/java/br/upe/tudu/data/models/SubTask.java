package br.upe.tudu.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sub_tasks")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SubTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_complete", columnDefinition = "boolean DEFAULT false")
    private boolean isComplete = false;

    public SubTask(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
