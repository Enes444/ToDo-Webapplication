package de.htwberlin.webtech.Entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * Die Klasse ToDo repräsentiert eine Aufgabe mit einem Titel, einem Fälligkeitsdatum,
 * einer Liste von Unteraufgaben und einem Status, ob die Aufgabe abgeschlossen ist.
 */
@Entity
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Eindeutige ID der Aufgabe

    private String title; // Titel der Aufgabe

    private Date dueDate; // Fälligkeitsdatum der Aufgabe

    @ElementCollection
    private List<Subtask> subtasks; // Liste der Unteraufgaben

    private boolean completed; // Status, ob die Aufgabe abgeschlossen ist

    // Getter und Setter

    /**
     * Getter für die ID der Aufgabe.
     * @return die ID der Aufgabe
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter für die ID der Aufgabe.
     * @param id die neue ID der Aufgabe
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter für den Titel der Aufgabe.
     * @return der Titel der Aufgabe
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter für den Titel der Aufgabe.
     * @param title der neue Titel der Aufgabe
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter für das Fälligkeitsdatum der Aufgabe.
     * @return das Fälligkeitsdatum der Aufgabe
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Setter für das Fälligkeitsdatum der Aufgabe.
     * @param dueDate das neue Fälligkeitsdatum der Aufgabe
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Getter für die Liste der Unteraufgaben.
     * @return die Liste der Unteraufgaben
     */
    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    /**
     * Setter für die Liste der Unteraufgaben.
     * @param subtasks die neue Liste der Unteraufgaben
     */
    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    /**
     * Getter für den Status, ob die Aufgabe abgeschlossen ist.
     * @return true, wenn die Aufgabe abgeschlossen ist, sonst false
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Setter für den Status, ob die Aufgabe abgeschlossen ist.
     * @param completed der neue Status der Aufgabe
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}