/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here

package com.example.todo.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.todo.model.Todo;
import com.example.todo.model.TodoRowMapper;
import com.example.todo.repository.TodoRepository;

import java.util.*;
import java.util.ArrayList;

@Service
public class TodoH2Service implements TodoRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Todo> getAll() {
        List<Todo> l = db.query("Select * from Todolist", new TodoRowMapper());
        ArrayList<Todo> arr = new ArrayList<>(l);
        return arr;
    }

    @Override
    public Todo AddNewData(Todo todo) {
        db.update("insert into Todolist(todo,status,priority) values(?,?,?)", todo.getTodo(), todo.getStatus(),
                todo.getPriority());
        Todo newTodo = db.queryForObject("Select * from Todolist where todo=? and status=?", new TodoRowMapper(),
                todo.getTodo(), todo.getStatus());
        return newTodo;
    }

    @Override
    public Todo getById(int id) {
        try {
            Todo todo = db.queryForObject("Select * from Todolist where id=?", new TodoRowMapper(), id);
            return todo;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Todo updateMethod(int id, Todo todo) {
        if (todo.getTodo() != null) {
            db.update("update Todolist set todo=? where id=?", todo.getTodo(), id);
        }
        if (todo.getPriority() != null) {
            db.update("update Todolist set priority=? where id=?", todo.getPriority(), id);
        }
        if (todo.getStatus() != null) {
            db.update("update Todolist set status=? where id=?", todo.getStatus(), id);
        }
        return getById(id);
    }

    @Override
    public void remove(int id) {
        db.update("delete from Todolist where id=?", id);
    }
}
