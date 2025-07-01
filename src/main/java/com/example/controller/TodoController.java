package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.TodoDTO;
import com.example.model.Todo;
import com.example.service.TodoService;

@Controller
@RequestMapping("/todo")
public class TodoController {
	private final TodoService todoService;

	// コンストラクタ
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	// 一覧表示
	@GetMapping("/list")
	public String listTodos(Model model) {
		List<TodoDTO> todos = todoService.getAllTodoDTOs();
		model.addAttribute("todos", todos);
		return "todo-list";
	}

	//登録フォームの表示
	@GetMapping("/create")
	public String showCreateForm(Model model) {
		model.addAttribute("todo", new Todo());
		return "todo-form";
	}

	//登録フォーム送信
	@PostMapping("/create")
	public String createTodo(@ModelAttribute Todo todo) {
		todoService.saveTodo(todo);
		return "redirect:/todo/list";
	}

	//削除
	@PostMapping("/delete")
	public String deleteTodo(@RequestParam Long id) {
		todoService.deleteTodo(id);
		return "redirect:/todo/list";
	}

	//	//編集フォームの表示
	//	@GetMapping("/edit")
	//	public String showEditForm(@RequestParam Long id,Model model) {
	//		Optional<Todo> optionalTodo = todoService.getTodoById(id);
	//		if(optionalTodo.isPresent()) {
	//			Todo todo = optionalTodo.get();
	//			model.addAttribute("todo",todo);
	//			return "todo-edit";
	//		}else {
	//			throw new RuntimeException("見つかりませんでしたよ");
	//		}
	//	}

	//編集フォームの表示
	@GetMapping("/edit")
	public String showEditForm(@RequestParam Long id, Model model) {
		Optional<Todo> optionalTodo = todoService.getTodoById(id);
		if (optionalTodo.isPresent()) {
			Todo todo = optionalTodo.get();
			TodoDTO dto = new TodoDTO(todo.getId(), todo.getTitle(), todo.isCompleted());
			model.addAttribute("todo", dto);
			return "todo-edit";
		} else {
			throw new RuntimeException("見つかりませんでしたよ");
		}
	}

	//編集
	@PostMapping("/update")
	public String updateTodo(@ModelAttribute TodoDTO todoDTO) {
		Todo todo = new Todo();
		todo.setId(todoDTO.getId());
		todo.setTitle(todoDTO.getTitle());
		todo.setCompleted(todoDTO.isCompleted());

		todoService.saveTodo(todo);
		return "redirect:/todo/list";
	}

}
