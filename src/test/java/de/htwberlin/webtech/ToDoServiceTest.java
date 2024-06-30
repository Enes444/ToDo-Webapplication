package de.htwberlin.webtech;

import de.htwberlin.webtech.Entity.ToDo;
import de.htwberlin.webtech.ToDoRepository.ToDoRepository;
import de.htwberlin.webtech.ToDoService.ToDoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Testklasse für den ToDoService.
 * Diese Klasse enthält Unit-Tests für die Methoden des ToDoService.
 */
class ToDoServiceTest {

    @Mock
    private ToDoRepository toDoRepository; // Mock-Objekt für das ToDoRepository

    @InjectMocks
    private ToDoService toDoService; // Der zu testende Service

    /**
     * Initialisierungsmethode, die vor jedem Test ausgeführt wird.
     * Hier werden die Mock-Objekte initialisiert.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test für die Methode getAllTodos.
     * Überprüft, ob alle Todos korrekt abgerufen werden.
     */
    @Test
    void testGetAllTodos() {
        // Vorbereitung der Testdaten
        ToDo todo1 = new ToDo();
        ToDo todo2 = new ToDo();
        List<ToDo> todos = Arrays.asList(todo1, todo2);

        // Mock-Verhalten definieren
        when(toDoRepository.findAll()).thenReturn(todos);

        // Methode aufrufen
        List<ToDo> result = toDoService.getAllTodos();

        // Überprüfungen
        assertEquals(2, result.size());
        verify(toDoRepository, times(1)).findAll();
    }

    /**
     * Test für die Methode addTodo.
     * Überprüft, ob ein neues Todo korrekt hinzugefügt wird.
     */
    @Test
    void testAddTodo() {
        // Vorbereitung der Testdaten
        ToDo todo = new ToDo();

        // Mock-Verhalten definieren
        when(toDoRepository.save(todo)).thenReturn(todo);

        // Methode aufrufen
        ToDo result = toDoService.addTodo(todo);

        // Überprüfungen
        assertNotNull(result);
        verify(toDoRepository, times(1)).save(todo);
    }

    /**
     * Test für die Methode updateTodo.
     * Überprüft, ob ein bestehendes Todo korrekt aktualisiert wird.
     */
    @Test
    void testUpdateTodo() {
        // Vorbereitung der Testdaten
        Long id = 1L;
        ToDo existingTodo = new ToDo();
        existingTodo.setId(id);
        ToDo updatedDetails = new ToDo();
        updatedDetails.setTitle("Updated Title");

        // Mock-Verhalten definieren
        when(toDoRepository.findById(id)).thenReturn(Optional.of(existingTodo));
        when(toDoRepository.save(existingTodo)).thenReturn(existingTodo);

        // Methode aufrufen
        ToDo result = toDoService.updateTodo(id, updatedDetails);

        // Überprüfungen
        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        verify(toDoRepository, times(1)).findById(id);
        verify(toDoRepository, times(1)).save(existingTodo);
    }

    /**
     * Test für die Methode deleteTodo.
     * Überprüft, ob ein Todo korrekt gelöscht wird.
     */
    @Test
    void testDeleteTodo() {
        // Vorbereitung der Testdaten
        Long id = 1L;

        // Mock-Verhalten definieren
        when(toDoRepository.existsById(id)).thenReturn(true);

        // Methode aufrufen
        boolean result = toDoService.deleteTodo(id);

        // Überprüfungen
        assertTrue(result);
        verify(toDoRepository, times(1)).existsById(id);
        verify(toDoRepository, times(1)).deleteById(id);
    }
}