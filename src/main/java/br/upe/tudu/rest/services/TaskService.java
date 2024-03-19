package br.upe.tudu.rest.services;

import br.upe.tudu.data.daos.TaskDAO;
import br.upe.tudu.data.models.Task;
import br.upe.tudu.rest.dtos.TaskDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    // ATRIBUTOS
    TaskDAO taskDAO;

    // MÉTODOS DE ACESSO
    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    // SERVIÇOS
    @Transactional
    public void saveTask(TaskDTO taskDTO) {
        this.taskDAO.save(taskDTO.toTask());
    }

    @Transactional
    public TaskDTO updateTask(TaskDTO taskDTO) {
        return TaskDTO.from(taskDAO.update(taskDTO.toTask()));
    }

    public TaskDTO findTask(Long id) {
        return TaskDTO.from(taskDAO.findById(id));
    }

    public List<TaskDTO> findAllUserTasks(Long userId) {
        return taskDAO.findTasksByUser(userId).stream().map(TaskDTO::from).collect(Collectors.toList());
    }

    @Transactional
    public void deleteTaskById(Long id) {
        taskDAO.deleteById(id);
    }

    @Transactional
    public void deleteTaskById(List<Long> ids) {
        taskDAO.deleteById(ids);
    }

    @Transactional
    public void deleteTask(Task task) {
        taskDAO.delete(task);
    }

    @Transactional
    public void deleteTask(List<Task> tasks) {
        taskDAO.delete(tasks);
    }

}
