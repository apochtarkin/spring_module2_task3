package ru.haulmont.pochtarkin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.haulmont.pochtarkin.entity.Book;
import ru.haulmont.pochtarkin.repository.BookRepository;
import ru.haulmont.pochtarkin.service.BookService;

import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Value("${author_name:author}")
    private String author_name;

    @Value("${spring.profiles.active}")
    private String profile_active;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public String getAllBook() {
        StringBuilder strB = new StringBuilder();

        if (profile_active.equals("dev") && bookRepository.count() == 0) {
            Book book = new Book();
            book.setName("dev_book1");
            book.setYear(2000);
            book.setAuthor("dev_author1");
            bookRepository.save(book);
            book = new Book();
            book.setName("dev_book2");
            book.setYear(2001);
            book.setAuthor("dev_author2");
            bookRepository.save(book);
            book = new Book();
            book.setName("dev_book3");
            book.setYear(2002);
            book.setAuthor("dev_author3");
            bookRepository.save(book);
        }
        for (Book book : bookRepository.findAll()) {
            strB.append("Книга: " + book.getName() + ", год: " + book.getYear() + ", " + author_name + ": " + book.getAuthor() + "<br>");
        }
        if (strB.toString().isEmpty())
            strB.append("Книг нет");
        return strB.toString();
    }
}
