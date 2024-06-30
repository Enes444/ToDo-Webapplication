package de.htwberlin.webtech;

import de.htwberlin.webtech.Entity.Subtask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Die Klasse SubtaskTest enthält Unit-Tests für die Subtask-Klasse.
 */
public class SubtaskTest {

    /**
     * Testet die Erstellung einer Subtask-Instanz und überprüft die Initialwerte.
     */
    @Test
    public void testSubtaskCreation() {
        Subtask subtask = new Subtask();
        subtask.setText("Test Subtask");
        subtask.setCompleted(false);

        // Überprüft, ob der Text korrekt gesetzt wurde
        assertEquals("Test Subtask", subtask.getText());
        // Überprüft, ob der Status "completed" korrekt gesetzt wurde
        assertFalse(subtask.isCompleted());
    }

    /**
     * Testet das Setzen des "completed"-Status einer Subtask-Instanz.
     */
    @Test
    public void testSubtaskCompletion() {
        Subtask subtask = new Subtask();
        subtask.setText("Test Subtask");
        subtask.setCompleted(false);

        // Setzt den Status "completed" auf true
        subtask.setCompleted(true);

        // Überprüft, ob der Status "completed" korrekt gesetzt wurde
        assertTrue(subtask.isCompleted());
    }
}