package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mappper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @RequestMapping(method = RequestMethod.GET, value = "getTask")

    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException{
        return taskMapper.mapToTaskDto(
                service.getTask(taskId).orElseThrow(TaskNotFoundException::new)
        );
    }


    @RequestMapping(method=RequestMethod.DELETE,value="deleteTask, taskId")
    public void deleteTask(Long taskId) {
        service.deleteTask(taskId);
    }


//    @RequestMapping(method=RequestMethod.PUT, value="putTask")
//    public TaskDto updateTask(TaskDto taskDto) {
//        return new TaskDto(1L, "Edited test title", "Test content");
//    }

    @PostMapping(value="postTask")

    @RequestMapping(method=RequestMethod.POST,value = "createTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        Task task=taskMapper.mapToTask(taskDto);
        service.saveTask(task);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = service.saveTask(task);
        return taskMapper.mapToTaskDto(savedTask);
    }
}
