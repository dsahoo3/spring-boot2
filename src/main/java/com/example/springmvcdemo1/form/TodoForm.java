package com.example.springmvcdemo1.form;

import javax.mvc.binding.MvcBinding;
import javax.ws.rs.FormParam;

public class TodoForm {
    
    @FormParam("id")
    private String id;

    @FormParam("task")
    private String task;

    @FormParam("description")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
