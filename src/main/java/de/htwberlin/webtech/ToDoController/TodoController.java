package de.htwberlin.webtech.TaskController;

import de.htwberlin.webtech.Entity.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    @GetMapping
    public String getTasks(Model model) {
        model.addAttribute("tasks", tasks);
        return "task";
    }

    @GetMapping("/new")
    public String showTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task_form";
    }

    @PostMapping
    public String addTask(@ModelAttribute Task task) {
        tasks.add(task);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Task task = tasks.get(id);
        model.addAttribute("task", task);
        return "task_edit_form";
    }

    @PostMapping("/{id}/edit")
    public String editTask(@PathVariable("id") int id, @ModelAttribute Task updatedTask) {
        Task task = tasks.get(id);
        task.setName(updatedTask.getName());
        // We can update other properties here if needed
        tasks.set(id, task);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable("id") int id) {
        tasks.remove(id);
        return "redirect:/tasks";
    }
}
