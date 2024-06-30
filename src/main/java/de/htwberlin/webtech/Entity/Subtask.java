package de.htwberlin.webtech.Entity;

import jakarta.persistence.Embeddable;

/**
 * Die Klasse Subtask repräsentiert eine Unteraufgabe, die in eine übergeordnete Aufgabe eingebettet werden kann.
 * Sie ist als @Embeddable markiert, was bedeutet, dass sie in eine andere Entität eingebettet werden kann.
 */
@Embeddable
public class Subtask {
    private String text; // Der Text der Unteraufgabe
    private boolean completed; // Der Status, ob die Unteraufgabe abgeschlossen ist

    /**
     * Getter für den Text der Unteraufgabe.
     * @return der Text der Unteraufgabe
     */
    public String getText() {
        return text;
    }

    /**
     * Setter für den Text der Unteraufgabe.
     * @param text der neue Text der Unteraufgabe
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Getter für den Status, ob die Unteraufgabe abgeschlossen ist.
     * @return true, wenn die Unteraufgabe abgeschlossen ist, sonst false
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Setter für den Status, ob die Unteraufgabe abgeschlossen ist.
     * @param completed der neue Status der Unteraufgabe
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}