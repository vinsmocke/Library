package com.myapp.library.repositories;

import com.myapp.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findBookById(int id);
    void deleteBookById(int id);
    List<Book> findBookByName(String name);
}
