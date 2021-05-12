package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskController taskController;

    @Test
    void getEmptyTaskList() throws Exception {
        //Given
        //List<TaskDto> tasksList=List.of(new TaskDto(1L,"Task 1", "1st Task content"));
        when(taskController.getTasks()).thenReturn(List.of());
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void getTaskTest() throws Exception {
        //Given
        // List<TaskDto> taskDtoList = List.of(new TaskDto(1L, "Task 1", "1st Task content"));
        // List<Task> tasks = List.of();
        List<TaskDto> tasksList = new ArrayList<>();
        TaskDto tasksDto = new TaskDto(1L, "Task 1", "1st Task content");
        tasksList.add(tasksDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(tasksList);

        when(taskController.getTask(1L)).thenReturn(tasksDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Task 1")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("1st Task content")));
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Task 1")));

    }

    @Test
    void deleteTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "task 1", "XYZ");

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                        //.param("task 1", "1"))

                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Creating Task", "Create");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders.post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Updated task", "ABC");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders.put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}