package de.htwberlin.webtech.ToDoController;


import de.htwberlin.webtech.Entity.ToDo;
import de.htwberlin.webtech.ToDoService.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private ToDoService toDoService;

    // Alle Todos abrufen
    @GetMapping
    public List<ToDo> getAllTodos() {
        return toDoService.getAllTodos();
    }

    // Einzelnes Todo abrufen
    @GetMapping("/{id}")
    public ToDo getTodoById(@PathVariable Long id) {
        Optional<ToDo> todoOptional = toDoService.getTodoById(id);
        return todoOptional.orElse(null); // Wenn das Todo nicht gefunden wird, wird null zurückgegeben
    }

    // Neues Todo hinzufügen
    @PostMapping
    public ToDo addTodo(@RequestBody ToDo todo) {
        return toDoService.addTodo(todo);
    }

    // Existierendes Todo aktualisieren
    @PutMapping("/{id}")
    public ToDo updateTodo(@PathVariable Long id, @RequestBody ToDo toDoDetails) {
        return toDoService.updateTodo(id, toDoDetails);
    }

    // Todo löschen
    @DeleteMapping("/{id}")
    public boolean deleteTodo(@PathVariable Long id) {
        return toDoService.deleteTodo(id);
    }
}