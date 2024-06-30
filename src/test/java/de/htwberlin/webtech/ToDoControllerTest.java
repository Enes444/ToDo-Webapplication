package de.htwberlin.webtech;

import de.htwberlin.webtech.Entity.ToDo;
import de.htwberlin.webtech.ToDoController.TodoController;
import de.htwberlin.webtech.ToDoService.ToDoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Die Klasse ToDoControllerTest enthält Unit-Tests für die ToDoController-Klasse.
 */
public class ToDoControllerTest {

    @Mock
    private ToDoService toDoService; // Mock des ToDoService

    @InjectMocks
    private TodoController toDoController; // Controller, der getestet wird

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Testet die Aktualisierung eines bestehenden Todos.
     */
    @Test
    public void testUpdateTodo() {
        ToDo existingToDo = new ToDo();
        existingToDo.setId(1L);
        existingToDo.setTitle("Existing ToDo");
        existingToDo.setDueDate(new Date());
        existingToDo.setCompleted(false);

        ToDo updatedToDoDetails = new ToDo();
        updatedToDoDetails.setTitle("Updated ToDo");
        updatedToDoDetails.setDueDate(new Date());
        updatedToDoDetails.setCompleted(true);

        ToDo updatedToDo = new ToDo();
        updatedToDo.setId(1L);
        updatedToDo.setTitle("Updated ToDo");
        updatedToDo.setDueDate(new Date());
        updatedToDo.setCompleted(true);

        when(toDoService.updateTodo(1L, updatedToDoDetails)).thenReturn(updatedToDo);

        ResponseEntity<ToDo> response = toDoController.updateTodo(1L, updatedToDoDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated ToDo", response.getBody().getTitle());
        assertTrue(response.getBody().isCompleted());
    }

    /**
     * Testet das Abrufen aller Todos.
     */
    @Test
    public void testGetAllTodos() {
        List<ToDo> mockTodos = new ArrayList<>();
        ToDo todo1 = new ToDo();
        todo1.setId(1L);
        todo1.setTitle("Todo 1");
        todo1.setDueDate(new Date());
        todo1.setCompleted(false);
        mockTodos.add(todo1);

        ToDo todo2 = new ToDo();
        todo2.setId(2L);
        todo2.setTitle("Todo 2");
        todo2.setDueDate(new Date());
        todo2.setCompleted(true);
        mockTodos.add(todo2);

        when(toDoService.getAllTodos()).thenReturn(mockTodos);

        List<ToDo> result = toDoController.getAllTodos();

        assertEquals(2, result.size());
        assertEquals("Todo 1", result.get(0).getTitle());
        assertEquals("Todo 2", result.get(1).getTitle());
    }

    /**
     * Testet das Hinzufügen eines neuen Todos.
     */
    @Test
    public void testAddTodo() {
        ToDo newToDo = new ToDo();
        newToDo.setTitle("New ToDo");
        newToDo.setDueDate(new Date());
        newToDo.setCompleted(false);

        when(toDoService.addTodo(newToDo)).thenReturn(newToDo);

        ToDo addedToDo = toDoController.createTodo(newToDo);

        assertEquals("New ToDo", addedToDo.getTitle());
        assertFalse(addedToDo.isCompleted());
    }

    /**
     * Testet das Löschen eines Todos.
     */
    @Test
    public void testDeleteTodo() {
        when(toDoService.deleteTodo(1L)).thenReturn(true);

        ResponseEntity<Void> response = toDoController.deleteTodo(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}