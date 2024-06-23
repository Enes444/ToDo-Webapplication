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
    private ToDoRepository toDoRepository;

    public List<ToDo> getAllTodos() {
        return toDoRepository.findAll();
    }

    public Optional<ToDo> getTodoById(Long id) {
        return toDoRepository.findById(id);
    }

    public ToDo addTodo(ToDo todo) {
        return toDoRepository.save(todo);
    }

    public ToDo updateTodo(Long id, ToDo toDoDetails) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (optionalToDo.isPresent()) {
            ToDo existingToDo = optionalToDo.get();
            existingToDo.setTitle(toDoDetails.getTitle());
            existingToDo.setDescription(toDoDetails.getDescription());
            existingToDo.setCompleted(toDoDetails.isCompleted());
            return toDoRepository.save(existingToDo);
        }
        return null;
    }

    public boolean deleteTodo(Long id) {
        if (toDoRepository.existsById(id)) {
            toDoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}