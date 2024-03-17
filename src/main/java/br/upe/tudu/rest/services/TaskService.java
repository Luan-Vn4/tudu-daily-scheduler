package br.upe.tudu.rest.services;

import br.upe.tudu.data.daos.TaskDAO;
import br.upe.tudu.data.models.Task;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    TaskDAO taskDAO;

    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Transactional
    public void saveTask(Task task) {
        this.taskDAO.save(task);
    }

    @Transactional
    public Task updateTask(Task task) {
        return taskDAO.update(task);
    }

    @Transactional
    public void deleteTaskById(Long id) {
        taskDAO.deleteById(id);
    }

    @Transactional
    public void deleteTaskById(List<Long> ids) {
        taskDAO.deleteById(ids);
    }

}
