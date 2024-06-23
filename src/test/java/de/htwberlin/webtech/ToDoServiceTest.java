package de.htwberlin.webtech;



import de.htwberlin.webtech.Entity.ToDo;
import de.htwberlin.webtech.ToDoRepository.ToDoRepository;
import de.htwberlin.webtech.ToDoService.ToDoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ToDoServiceTest {

    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoService toDoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTodoById() {
        ToDo mockToDo = new ToDo();
        mockToDo.setId(1L);
        mockToDo.setTitle("Test ToDo");
        mockToDo.setDescription("Test Description");
        mockToDo.setCompleted(false);

        when(toDoRepository.findById(1L)).thenReturn(Optional.of(mockToDo));

        Optional<ToDo> result = toDoService.getTodoById(1L);

        assertEquals("Test ToDo", result.get().getTitle());
        assertEquals("Test Description", result.get().getDescription());
        assertEquals(false, result.get().isCompleted());
    }

    @Test
    public void testAddTodo() {
        ToDo newToDo = new ToDo();
        newToDo.setTitle("New ToDo");
        newToDo.setDescription("New Description");
        newToDo.setCompleted(false);

        when(toDoRepository.save(newToDo)).thenReturn(newToDo);

        ToDo addedToDo = toDoService.addTodo(newToDo);

        assertEquals("New ToDo", addedToDo.getTitle());
        assertEquals("New Description", addedToDo.getDescription());
        assertEquals(false, addedToDo.isCompleted());
    }

    @Test
    public void testUpdateTodo() {
        ToDo existingToDo = new ToDo();
        existingToDo.setId(1L);
        existingToDo.setTitle("Existing ToDo");
        existingToDo.setDescription("Existing Description");
        existingToDo.setCompleted(false);

        ToDo updatedToDoDetails = new ToDo();
        updatedToDoDetails.setTitle("Updated ToDo");
        updatedToDoDetails.setDescription("Updated Description");
        updatedToDoDetails.setCompleted(true);

        when(toDoRepository.findById(1L)).thenReturn(Optional.of(existingToDo));
        when(toDoRepository.save(existingToDo)).thenReturn(existingToDo);

        ToDo updatedToDo = toDoService.updateTodo(1L, updatedToDoDetails);

        assertEquals("Updated ToDo", updatedToDo.getTitle());
        assertEquals("Updated Description", updatedToDo.getDescription());
        assertEquals(true, updatedToDo.isCompleted());
    }

    @Test
    public void testDeleteTodo() {
        when(toDoRepository.existsById(1L)).thenReturn(true);

        boolean isDeleted = toDoService.deleteTodo(1L);

        assertTrue(isDeleted);
    }
}