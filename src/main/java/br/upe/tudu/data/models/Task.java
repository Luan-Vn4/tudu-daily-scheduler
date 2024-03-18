package br.upe.tudu.data.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tasks")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@ToString
public class Task {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "date")
    private LocalDate date;

    // orphanRemoval é necessário para remover as subtasks no merge
    // OnDeleteAction.CASCADE é necessário para remover as subtasks quando a task é removida por CASCADE também
    // nullable=false necessário, pois evita que foreign keys sejam definidas como nulas no remove ao invés de apagá-las
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name = "sub_tasks_tasks_fk"), nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<SubTask> subTasks;

    // onDeleteAction.CASCADE é necessário para apagar as tasks quando o seu user for apagado
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name="tasks_users_fkey"), nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

}
