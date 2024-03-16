package br.upe.tudu.data.daos;

import br.upe.tudu.data.models.Task;
import br.upe.tudu.data.models.User;
import java.util.List;

public interface TaskDAO extends DAO <User, Long> {
    List<Task> findTasksByUser(Long id);

}
