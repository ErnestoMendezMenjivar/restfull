package com.api.restfull.Controller;

import com.api.restfull.Model.Task;
import com.api.restfull.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired

    private TodoRepository todoRepository;

    @GetMapping(value = "/api/")
    public String holamundo() {
        return "HOLA PLUTO";
    }


    //mostrar las tareas
    @GetMapping(value = "/api/tasks")
    public List<Task> getTasks() {
        return todoRepository.findAll();
    }


    //crear tareas
    @PostMapping(value = "/api/save")
    public ResponseEntity<String> save(@RequestBody Task task) {
        todoRepository.save(task);
        String message = "{\"message\": \"Tarea guardada con éxito\"}";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //editar tareas
    @PutMapping(value = "/api/editar/{id}")
    public ResponseEntity<String> editar( @PathVariable long id, @RequestBody Task task){
        Task editar = todoRepository.findById(id).get();
        editar.setTitle(task.getTitle());
        editar.setDescription(task.getDescription());

        todoRepository.save(editar);

        String message = "{\"message\": \"Tarea editada con éxito\"}";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //eliminar tareas
    @DeleteMapping(value = "/api/delete/{id}")
    public ResponseEntity<String> delete( @PathVariable long id){
        Task delete = todoRepository.findById(id).get();
        todoRepository.delete(delete);
        String message = "{\"message\": \"Tarea eliminada con éxito\"}";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
