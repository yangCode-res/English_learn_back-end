package com.yang.english.dao;



import com.yang.english.entiy.ListeningBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListeningBookRepository extends JpaRepository<ListeningBook, Long> {
    List<ListeningBook> findByParentIdIsNull();
}

