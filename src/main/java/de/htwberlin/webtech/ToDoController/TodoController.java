package de.htwberlin.webtech.ToDoController;

import de.htwberlin.webtech.Entity.ToDo;
import de.htwberlin.webtech.ToDoService.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-Controller für die Verwaltung von Todos.
 */
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private ToDoService toDoService;

    /**
     * Methode zum Abrufen aller Todos.
     * @return eine Liste aller Todos
     */
    @GetMapping
    public List<ToDo> getAllTodos() {
        return toDoService.getAllTodos();
    }

    /**
     * Methode zum Erstellen eines neuen Todos.
     * @param todo das neue Todo
     * @return das erstellte Todo
     */
    @PostMapping
    public ToDo createTodo(@RequestBody ToDo todo) {
        return toDoService.addTodo(todo);
    }

    /**
     * Methode zum Aktualisieren eines bestehenden Todos.
     * @param id die ID des zu aktualisierenden Todos
     * @param toDoDetails die neuen Details des Todos
     * @return das aktualisierte Todo oder eine 404-Not-Found-Antwort, wenn das Todo nicht gefunden wurde
     */
    @PutMapping("/{id}")
    public ResponseEntity<ToDo> updateTodo(@PathVariable Long id, @RequestBody ToDo toDoDetails) {
        ToDo updatedTodo = toDoService.updateTodo(id, toDoDetails);
        if (updatedTodo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTodo);
    }

    /**
     * Methode zum Löschen eines Todos.
     * @param id die ID des zu löschenden Todos
     * @return eine 204-No-Content-Antwort, wenn das Todo erfolgreich gelöscht wurde, sonst eine 404-Not-Found-Antwort
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        boolean deleted = toDoService.deleteTodo(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}