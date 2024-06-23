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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ToDoControllerTest {

    @Mock
    private ToDoService toDoService;

    @InjectMocks
    private TodoController todoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
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

        ToDo updatedToDo = new ToDo();
        updatedToDo.setId(1L);
        updatedToDo.setTitle("Updated ToDo");
        updatedToDo.setDescription("Updated Description");
        updatedToDo.setCompleted(true);

        when(toDoService.updateTodo(1L, updatedToDoDetails)).thenReturn(updatedToDo);

        ResponseEntity<ToDo> response = todoController.updateTodo(1L, updatedToDoDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated ToDo", response.getBody().getTitle());
        assertEquals("Updated Description", response.getBody().getDescription());
        assertEquals(true, response.getBody().isCompleted());
    }

    @Test
    public void testGetAllTodos() {
        List<ToDo> mockTodos = new ArrayList<>();
        ToDo todo1 = new ToDo();
        todo1.setId(1L);
        todo1.setTitle("Todo 1");
        todo1.setDescription("Description 1");
        todo1.setCompleted(false);
        mockTodos.add(todo1);

        ToDo todo2 = new ToDo();
        todo2.setId(2L);
        todo2.setTitle("Todo 2");
        todo2.setDescription("Description 2");
        todo2.setCompleted(true);
        mockTodos.add(todo2);

        when(toDoService.getAllTodos()).thenReturn(mockTodos);

        List<ToDo> result = todoController.getAllTodos();

        assertEquals(2, result.size());
        assertEquals("Todo 1", result.get(0).getTitle());
        assertEquals("Todo 2", result.get(1).getTitle());
    }

    @Test
    public void testGetTodoById() {
        ToDo mockToDo = new ToDo();
        mockToDo.setId(1L);
        mockToDo.setTitle("Test ToDo");
        mockToDo.setDescription("Test Description");
        mockToDo.setCompleted(false);

        when(toDoService.getTodoById(1L)).thenReturn(Optional.of(mockToDo));

        ResponseEntity<ToDo> result = todoController.getTodoById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Test ToDo", result.getBody().getTitle());
        assertEquals("Test Description", result.getBody().getDescription());
    }

    @Test
    public void testAddTodo() {
        ToDo newToDo = new ToDo();
        newToDo.setTitle("New ToDo");
        newToDo.setDescription("New Description");
        newToDo.setCompleted(false);

        when(toDoService.addTodo(newToDo)).thenReturn(newToDo);

        ToDo addedToDo = todoController.addTodo(newToDo);

        assertEquals("New ToDo", addedToDo.getTitle());
        assertEquals("New Description", addedToDo.getDescription());
        assertEquals(false, addedToDo.isCompleted());
    }

    @Test
    public void testDeleteTodo() {
        when(toDoService.deleteTodo(1L)).thenReturn(true);

        ResponseEntity<Void> response = todoController.deleteTodo(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}