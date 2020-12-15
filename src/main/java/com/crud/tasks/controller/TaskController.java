package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mappper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/task")

public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;
    Long id;

    @Autowired
    public TaskController(DbService service, TaskMapper taskMapper) {
        this.service = service;
        this.taskMapper = taskMapper;
    }

    @RequestMapping(method = RequestMethod.GET,value="getTasks")
    //wyciaganie z serwisu
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
        //return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{taskId}")

    public Optional<Task> getTask(Long taskId){

        return service.getTask(taskId);
    }


    @RequestMapping(method=RequestMethod.DELETE,value="deleteTask")
    public void deleteTask(Long taskId) {
    }


    @RequestMapping(method=RequestMethod.PUT, value="putTask")
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping(value="postTask")
    @RequestMapping(method=RequestMethod.POST,value = "postTask")
    public void createTask(TaskDto taskDto) {
    }
}
