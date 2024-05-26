package de.htwberlin.webtech.ToDoService;

import de.htwberlin.webtech.Entity.ToDo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ToDoService {
    private final List<ToDo> todos = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    // Alle Todos abrufen
    public List<ToDo> getAllTodos() {
        return new ArrayList<>(todos);
    }

    // Einzelnes Todo anhand der ID abrufen
    public Optional<ToDo> getTodoById(Long id) {
        return todos.stream().filter(todo -> todo.getId().equals(id)).findFirst();
    }

    // Neues Todo hinzufügen
    public ToDo addTodo(ToDo todo) {
        todo.setId(counter.incrementAndGet());
        todos.add(todo);
        return todo;
    }

    // Existierendes Todo aktualisieren
    public ToDo updateTodo(Long id, ToDo toDoDetails) {
        Optional<ToDo> todoOptional = getTodoById(id);
        if (todoOptional.isPresent()) {
            ToDo todo = todoOptional.get();
            todo.setTitle(toDoDetails.getTitle());
            todo.setDescription(toDoDetails.getDescription());
            return todo;
        }
        return null; // Todo nicht gefunden
    }

    // Todo löschen
    public boolean deleteTodo(Long id) {
        return todos.removeIf(todo -> todo.getId().equals(id));
    }
}
