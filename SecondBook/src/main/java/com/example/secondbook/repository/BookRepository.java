package com.example.secondbook.repository;

import com.example.secondbook.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book,Integer> {
    //List<Book> findByTitle(String title);
}
