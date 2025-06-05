package br.com.wesguedesas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wesguedesas.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{}
