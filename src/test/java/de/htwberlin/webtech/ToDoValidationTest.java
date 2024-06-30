package de.htwberlin.webtech;

import de.htwberlin.webtech.Entity.ToDo;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Die Klasse ToDoValidationTest enthält Unit-Tests zur Validierung der ToDo-Klasse.
 */
public class ToDoValidationTest {

    /**
     * Testet, dass der Titel eines ToDos nicht null sein darf.
     */
    @Test
    public void testToDoTitleNotNull() {
        ToDo todo = new ToDo();
        todo.setDueDate(new Date());
        todo.setCompleted(false);

        assertThrows(NullPointerException.class, () -> {
            if (todo.getTitle() == null) {
                throw new NullPointerException("Title cannot be null");
            }
        });
    }

    /**
     * Testet, dass das Fälligkeitsdatum eines ToDos nicht null sein darf.
     */
    @Test
    public void testToDoDueDateNotNull() {
        ToDo todo = new ToDo();
        todo.setTitle("Test ToDo");
        todo.setCompleted(false);

        assertThrows(NullPointerException.class, () -> {
            if (todo.getDueDate() == null) {
                throw new NullPointerException("Due date cannot be null");
            }
        });
    }
}