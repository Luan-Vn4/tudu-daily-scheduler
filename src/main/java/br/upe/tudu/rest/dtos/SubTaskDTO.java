package br.upe.tudu.rest.dtos;

import br.upe.tudu.data.models.SubTask;

public record SubTaskDTO(Long id, String name, boolean isComplete) {

    public SubTask toSubTask() {
        return new SubTask(id(), name(), isComplete());
    }

    public static SubTaskDTO from(SubTask subTask) {
        return new SubTaskDTO(subTask.getId(), subTask.getName(), subTask.isComplete());
    }

}
