package ru.haulmont.pochtarkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.haulmont.pochtarkin.entity.Book;
import ru.haulmont.pochtarkin.repository.BookRepository;
import ru.haulmont.pochtarkin.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookService bookService,
                          BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBook() {
        return new ResponseEntity(bookService.getAllBook(), HttpStatus.OK);
    }

    @GetMapping("/create")
    public ResponseEntity<Book> createBook(@RequestParam String name, @RequestParam int year, @RequestParam String author) {
        Book book = new Book();
        book.setName(name);
        book.setYear(year);
        book.setAuthor(author);
        bookRepository.save(book);
        return new ResponseEntity("Книга создана", HttpStatus.OK);

    }
}
