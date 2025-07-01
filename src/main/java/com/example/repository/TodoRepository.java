package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {//Todoクラスに記載されているエンティティを扱うよ。そのエンティティの主キーはLONGだよ
    // 必要なら独自の検索メソッドをここに追加できる（例：findByTitle など）
}