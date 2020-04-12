package com.example.springmvcdemo1.controller;

import com.example.springmvcdemo1.form.TodoForm;
import com.example.springmvcdemo1.model.Todo;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.View;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@Controller
@Stateless
@Path("/todo")
public class TodoController {

	
    @PersistenceContext
    EntityManager em;

    @Inject
    Models models;

//    @GET
//    @View("todo/all-todos.html")
//    public void getAllTodos() {
//        List<Todo> allTodos = em.createNamedQuery("Todo.findAll", Todo.class).getResultList();
//        models.put("todos", allTodos);
//    }

    @GET
    @Path("/create")
    public String getCreateTodoForm() {
        return "todo/create-todo.html";
    }

    @POST
    @Path("/create")
    public String create(@BeanParam TodoForm todoForm) {
        Todo newTodo = new Todo();
        newTodo.setDescription(todoForm.getDescription());
        newTodo.setTask(todoForm.getTask());
        
        em.persist(newTodo);
        
        return "redirect:/todo";
    }
    
    @GET
    @Path("/update/{id}")
    @View("todo/update-todo.html")
    public void getUpdateTodoForm(@PathParam("id") Long id) {
        Todo todo = em.find(Todo.class, id);
        
        models.put("todo", todo);
    }
    
    @POST
    @Path("/update")
    public String update(@BeanParam TodoForm todoForm) {        
        Todo getTodo = em.find(Todo.class, Long.parseLong(todoForm.getId()));
        
        getTodo.setDescription(todoForm.getDescription());
        getTodo.setTask(todoForm.getTask());
        
        em.merge(getTodo);
        
        return "redirect:/todo";
    }
    
    @GET
    @Path("/delete/{id}")
    public String deleteTodo(@PathParam("id") Long id) {
        Todo todo = em.find(Todo.class, id);
        
        if (!em.contains(todo)) {
            todo = em.merge(todo);
        }

        em.remove(todo);
        return "redirect:/todo";
    }

}