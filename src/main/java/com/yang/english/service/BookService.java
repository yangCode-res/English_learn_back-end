package com.yang.english.service;

import com.yang.english.dao.BookRepository;
import com.yang.english.entiy.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public Book getBookById(Long id) {
        return bookRepository.findByBookId(id);
    }
}
