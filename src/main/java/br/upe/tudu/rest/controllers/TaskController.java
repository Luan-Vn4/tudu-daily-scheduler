package br.upe.tudu.rest.controllers;

import br.upe.tudu.data.models.Task;
import br.upe.tudu.rest.dtos.TaskDTO;
import br.upe.tudu.rest.services.TaskService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/tasks")
@RestController
public class TaskController{

    // ATRIBUTOS
    TaskService taskService;

    // MÉTODOS DE ACESSO
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // MÉTODOS HTTP
    @PostMapping("/")
    public void saveTask(@RequestBody TaskDTO taskDTO) {
        taskService.saveTask(taskDTO);
    }

    @GetMapping(value = "/", params = "id")
    public TaskDTO findTask(@RequestParam(value = "id") Long id) {
        return taskService.findTask(id);
    }

    @GetMapping(value = "/", params = "user-id")
    public List<TaskDTO> findTaskByUserId(@RequestParam(value = "user-id") Long userId) {
        return taskService.findAllUserTasks(userId);
    }

    @PutMapping("/")
    public TaskDTO updateTask(@RequestBody TaskDTO task) {
        return taskService.updateTask(task);
    }

    @DeleteMapping(value = "/", params = "id")
    public void deleteTaskById(@RequestParam(value = "id") Long id) {
            taskService.deleteTaskById(id);
    }

    @DeleteMapping(value = "/", params = "ids")
    public void deleteTaskById(@RequestParam(value = "ids") List<Long> ids) {
        taskService.deleteTaskById(ids);
    }

    @DeleteMapping("/one")
    public void deleteTask(@RequestBody Task task) {
        taskService.deleteTask(task);
    }

    @DeleteMapping("/multiple")
    public void deleteTask(@RequestBody List<Task> tasks) {
        taskService.deleteTask(tasks);
    }

}
