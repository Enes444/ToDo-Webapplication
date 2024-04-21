package de.htwberlin.webtech.TaskController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class TaskController {

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        List<String> tasks = Arrays.asList("Aufgabe 1: Milestone 1", "Aufgabe 2: Produktionswirtschaft", "Aufgabe 3");
        model.addAttribute("tasks", tasks);
        return "task";
    }
}
