package de.htwberlin.webtech.ToDoService;

import de.htwberlin.webtech.Entity.ToDo;
import de.htwberlin.webtech.ToDoRepository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository todoRepository;

    // Alle Todos abrufen
    public List<ToDo> getAllTodos() {
        return todoRepository.findAll();
    }

    // Einzelnes Todo anhand der ID abrufen
    public Optional<ToDo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    // Neues Todo hinzufügen
    public ToDo addTodo(ToDo todo) {
        return todoRepository.save(todo);
    }

    // Existierendes Todo aktualisieren
    public ToDo updateTodo(Long id, ToDo toDoDetails) {
        Optional<ToDo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            ToDo todo = todoOptional.get();
            todo.setTitle(toDoDetails.getTitle());
            todo.setDescription(toDoDetails.getDescription());
            return todoRepository.save(todo);
        }
        return null; // Todo nicht gefunden
    }

    // Todo löschen
    public boolean deleteTodo(Long id) {
        Optional<ToDo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            todoRepository.deleteById(id);
            return true;
        }
        return false; // Todo nicht gefunden
    }
}
