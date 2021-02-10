package com.crud.tasks.domain;

import com.crud.tasks.service.TrelloService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
//@AllArgsConstructor
public class Mail {

    TrelloService trelloService;

    public String mailTo;
    public String subject;
    public String message;
    public String toCc;


    @Builder
    static class MailBuilder {
        private String mailTo;
        private String subject;
        private String message;
        private String toCc;
    }

    @Override
    public String toString() {
        return "Mail{" +
                ", mailTo='" + mailTo + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", toCc='" + toCc + '\'' +
                '}';
    }
}
