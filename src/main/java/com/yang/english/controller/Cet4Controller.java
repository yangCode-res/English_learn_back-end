package com.yang.english.controller;

import com.yang.english.dao.Cet4Repository;
import com.yang.english.entiy.Cet4Word;
import com.yang.english.service.Cet4WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class Cet4Controller {
    private final Cet4Repository cet4Repository;
    @Autowired
    private Cet4WordService service;

    public Cet4Controller(Cet4Repository cet4Repository) {
        this.cet4Repository = cet4Repository;

    }

    @GetMapping("cet4words")
    public Page<Cet4Word> getAllWords(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size){
        return service.listAllWords(page, size);
    }
}
