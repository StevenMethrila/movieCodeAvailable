// Write your code here
package com.example.todo.repository;

import com.example.todo.model.Todo;
import java.util.*;

public interface TodoRepository {

    public ArrayList<Todo> getAll();

    public Todo AddNewData(Todo todo);

    public Todo getById(int id);

    public Todo updateMethod(int id, Todo todo);

    public void remove(int id);
}