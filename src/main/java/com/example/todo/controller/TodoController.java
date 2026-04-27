package com.example.todo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
public class TodoController {

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
        return "todo/new";
    }

    public record TodoListRow(Long id, String title, String status) {
    }
}
