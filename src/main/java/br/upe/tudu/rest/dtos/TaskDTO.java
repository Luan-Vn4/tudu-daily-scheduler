package br.upe.tudu.rest.dtos;

import br.upe.tudu.data.models.SubTask;
import br.upe.tudu.data.models.Task;
import br.upe.tudu.data.models.User;

import java.time.LocalDate;
import java.util.List;

public record TaskDTO(Long id, String name, String category, LocalDate date, List<SubTaskDTO> subTasks, Long userId) {

    public Task toTask() {
        List<SubTask> subTasks = subTasks().stream().map(SubTaskDTO::toSubTask).toList();
        User user = new User() {{setId(userId);}};
        return new Task(id(), name(), category(), date(), subTasks, user);
    }

    public static TaskDTO from(Task task) {
        List<SubTaskDTO> subTaskDTOS = task.getSubTasks().stream().map(SubTaskDTO::from).toList();
        return new TaskDTO(task.getId(), task.getName(), task.getCategory(),
                            task.getDate(), subTaskDTOS, task.getUser().getId());
    }

}
