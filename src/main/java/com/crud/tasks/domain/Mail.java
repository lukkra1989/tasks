package com.crud.tasks.domain;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mail {

    public String mailTo;
    public String subject;
    public String message;
    public String toCc;
}
