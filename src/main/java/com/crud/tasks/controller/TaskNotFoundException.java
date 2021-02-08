package com.crud.tasks.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/exception/")
public class TaskNotFoundException extends Exception{
}
