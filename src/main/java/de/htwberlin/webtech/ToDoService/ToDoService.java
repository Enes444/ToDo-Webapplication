package de.htwberlin.webtech.ToDoService;

import de.htwberlin.webtech.Entity.ToDo;
import de.htwberlin.webtech.ToDoRepository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Der ToDoService stellt die Geschäftslogik für die Verwaltung von Todos bereit.
 */
@Service
public class ToDoService {
    @Autowired
    private ToDoRepository toDoRepository; // Repository zur Verwaltung der Todos

    /**
     * Methode zum Abrufen aller Todos.
     * @return eine Liste aller Todos
     */
    public List<ToDo> getAllTodos() {
        return toDoRepository.findAll();
    }

    /**
     * Methode zum Hinzufügen eines neuen Todos.
     * @param todo das neue Todo
     * @return das hinzugefügte Todo
     */
    public ToDo addTodo(ToDo todo) {
        return toDoRepository.save(todo);
    }

    /**
     * Methode zum Abrufen eines Todos nach ID.
     * @param id die ID des zu abrufenden Todos
     * @return das Todo oder null, wenn das Todo nicht gefunden wurde
     */
    public ToDo findById(Long id) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        return optionalToDo.orElse(null);
    }

    /**
     * Methode zum Aktualisieren eines bestehenden Todos.
     * @param id die ID des zu aktualisierenden Todos
     * @param toDoDetails die neuen Details des Todos
     * @return das aktualisierte Todo oder null, wenn das Todo nicht gefunden wurde
     */
    public ToDo updateTodo(Long id, ToDo toDoDetails) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (optionalToDo.isPresent()) {
            ToDo existingToDo = optionalToDo.get();
            existingToDo.setTitle(toDoDetails.getTitle());
            existingToDo.setDueDate(toDoDetails.getDueDate());
            existingToDo.setSubtasks(toDoDetails.getSubtasks());
            existingToDo.setCompleted(toDoDetails.isCompleted());
            return toDoRepository.save(existingToDo);
        }
        return null;
    }

    /**
     * Methode zum Löschen eines Todos.
     * @param id die ID des zu löschenden Todos
     * @return true, wenn das Todo erfolgreich gelöscht wurde, sonst false
     */
    public boolean deleteTodo(Long id) {
        if (toDoRepository.existsById(id)) {
            toDoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}