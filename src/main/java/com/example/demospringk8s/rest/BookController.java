package com.example.demospringk8s.rest;

import com.example.demospringk8s.entity.BookEntity;
import com.example.demospringk8s.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<BookEntity> getAll() {
        return bookRepository.findAll();
    }

    @PostMapping
    public BookEntity addNew(@RequestBody BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        bookRepository.deleteById(id);
    }
}
