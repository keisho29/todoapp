package com.example.dto;

public class TodoDTO {
	private Long id;
	private String title;
	private boolean completed;
	
	public TodoDTO() {};

	public TodoDTO(Long id,String title,boolean completed) {
		this.setId(id);
		this.title=title;
		this.completed=completed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
}
