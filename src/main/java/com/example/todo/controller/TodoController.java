package com.example.todo.controller;

import java.util.List;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String showList(Model model) {
        List<TodoListRow> todoList = List.of(
                new TodoListRow(1L, "要件定義を作成する", "未着手"),
                new TodoListRow(2L, "一覧画面を実装する", "進行中"),
                new TodoListRow(3L, "単体テストを追加する", "完了"));

        model.addAttribute("todoList", todoList);
        return "todo/list";
    }

    @GetMapping("/new")
    public String showNewForm() {
        return "todo/form";
    }

    @PostMapping("/confirm")
    public String showConfirm(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam(name = "done", defaultValue = "false") Boolean done,
            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("done", done);
        return "todo/confirm";
    }

    @PostMapping("/complete")
    public String showComplete(
            @RequestParam("title") String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setCompleted(false);
        todoService.insert(todo);
        return "redirect:/todo";
    }

    public record TodoListRow(Long id, String title, String status) {
    }
}
