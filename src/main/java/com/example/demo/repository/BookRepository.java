package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Long>, PagingAndSortingRepository<Book, Long> {
    List<Book> findByUserId(Long userId);

    List<Book> findAll();
}
