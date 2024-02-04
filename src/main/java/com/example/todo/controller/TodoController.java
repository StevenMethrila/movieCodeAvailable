/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 */

// Write your code here

package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import com.example.todo.model.Todo;
import com.example.todo.service.TodoH2Service;

@RestController
public class TodoController {
    @Autowired
    private TodoH2Service ts;

    @GetMapping("/todos")
    public ArrayList<Todo> getMethod() {
        return ts.getAll();
    }

    @PostMapping("/todos")
    public Todo postMethod(@RequestBody Todo todo) {
        return ts.AddNewData(todo);
    }

    @GetMapping("/todos/{id}")
    public Todo getMethodById(@PathVariable("id") int id) {
        return ts.getById(id);
    }

    @PutMapping("/todos/{id}")
    public Todo putMethod(@PathVariable("id") int id, @RequestBody Todo todo) {
        return ts.updateMethod(id, todo);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteMethod(@PathVariable("id") int id) {
        ts.remove(id);
    }
}
