package ru.haulmont.pochtarkin.service;

import ru.haulmont.pochtarkin.entity.Book;

import java.util.List;

public interface BookService {
    String getAllBook();

    void createBook(Book book);
}
