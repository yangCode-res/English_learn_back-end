package com.yang.english.service;

import com.yang.english.dao.Cet4Repository;
import com.yang.english.entiy.Cet4Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class Cet4WordService {
    @Autowired
    private Cet4Repository repository;

    public Page<Cet4Word> listAllWords(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return repository.findAll(pageable);
    }
}
