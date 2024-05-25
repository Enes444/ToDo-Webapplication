package de.htwberlin.webtech.ToDoRepository;


import de.htwberlin.webtech.Entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
