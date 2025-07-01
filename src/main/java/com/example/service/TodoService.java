package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dto.TodoDTO;
import com.example.model.Todo;
import com.example.repository.TodoRepository;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    // コンストラクタでRepositoryを受け取る（依存注入）
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // 全件取得
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
    
    //ID指定取得
    public Optional<Todo> getTodoById(Long id) {
    	return todoRepository.findById(id);
    }
    
    //TodoDTOをTodoに変換
    public Todo convertToEntity(TodoDTO todoDTO) {
		Todo todo = new Todo();
		todo.setId(todoDTO.getId());
		todo.setTitle(todoDTO.getTitle());
		todo.setCompleted(todoDTO.isCompleted());
		return todo;
	}
    
    //TodoをTodoDTOに変換
    public TodoDTO convertToDTO(Todo todo) {
    	return new TodoDTO(todo.getId(),todo.getTitle(),todo.isCompleted());
		
	}
    
    //DTO使用の全件取得
    public List<TodoDTO> getAllTodoDTOs() {
    	List<Todo>todos=todoRepository.findAll();
    	List<TodoDTO>dtoList=new ArrayList<TodoDTO>();
    	for (Todo todo : todos) {
			TodoDTO dto = new TodoDTO(todo.getId(),todo.getTitle(),todo.isCompleted());//上記convertDTOを使用してコードをすっきりさせてもよい
			dtoList.add(dto);
		}
    	return dtoList;
		
	}

    // 登録
    public void saveTodo(Todo todo) {
        todoRepository.save(todo);
    }

    // 削除
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}