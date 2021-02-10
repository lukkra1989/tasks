package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;
    private static final String SUBJECT = "Tasks: Once a day email";

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {

        String message;

        long size = taskRepository.count();

        if (size == 0) {
            message = "Currently there are no tasks";
        } else if (size == 1) {
            message = "Currently in database you got: 1 task";
        } else
            message = "Currently in database you got: " + size + "tasks";

    }
}
