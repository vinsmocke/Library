package com.myapp.library.controllers;

import com.myapp.library.models.Book;
import com.myapp.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {
    @Autowired
    private BookRepository bookRepository;
    @GetMapping("/add")
    public String addBookPage(@ModelAttribute("book") Book book){
        return "moderator/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") @Valid Book book,
                          BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "moderator/add";
        }
        bookRepository.save(book);

        return "redirect:/catalog";
    }

    @GetMapping("/process-books")
    public String allBooks(Model model){
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);

        return "moderator/allBooks";
    }
    @DeleteMapping("/remove-book/{id}")
    public String removeBook(@PathVariable("id") int bookId){
       bookRepository.deleteBookById(bookId);

        return "redirect:/moderator/process-books";
    }
}
