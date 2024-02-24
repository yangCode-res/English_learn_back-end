package com.yang.english.controller;


import com.yang.english.dao.ListeningBookRepository;
import com.yang.english.entiy.ListeningBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/listening-books")
public class ListeningBookController {

    @Autowired
    private ListeningBookRepository listeningBookRepository;

    @GetMapping("/parents")
    public List<ListeningBook> getAllParentListeningBooks() {
        return listeningBookRepository.findByParentIdIsNull();
    }
}
