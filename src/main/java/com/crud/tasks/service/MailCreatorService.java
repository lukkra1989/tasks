package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message){

        List<String>functionality = new ArrayList<>();
                functionality.add("You can manage your tasks");
                functionality.add("Provides connection with Trello Account ");
                functionality.add("Application allows sending tasks to Trello");

        Context context=new Context();
        context.setVariable("message", message);
       //context.setVariable("tasks_url", "https://lukkra1989.github.io/");
        context.setVariable("task_url", "http://localhost:8080");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail",context);
    }

    public String buildDailyTasksCountInfoEmail(String message) {

        List<String> funcionality = new ArrayList<>();
        funcionality.add("You can manage your tasks");
        funcionality.add("Provides connection with Trello Account");
        funcionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("preview_message", message);
        context.setVariable("message", message);
        context.setVariable("tasks_url","http://localhost:8080/");
        context.setVariable("button","Visit website");
        context.setVariable("show_button", true);
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("admin_config", adminConfig);
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", funcionality);
        context.setVariable("bye_message", "Thank you for using our App! We hope see you soon!");

        return templateEngine.process("mail/created-trello-card-mail", context);
    }

}
