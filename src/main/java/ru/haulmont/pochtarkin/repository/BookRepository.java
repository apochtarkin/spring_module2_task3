package ru.haulmont.pochtarkin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.haulmont.pochtarkin.entity.Book;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
