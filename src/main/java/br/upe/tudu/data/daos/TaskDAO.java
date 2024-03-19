package br.upe.tudu.data.daos;

import br.upe.tudu.data.models.Task;
import java.util.List;

public interface TaskDAO extends DAO <Task, Long> {
    List<Task> findTasksByUser(Long userId);

}
