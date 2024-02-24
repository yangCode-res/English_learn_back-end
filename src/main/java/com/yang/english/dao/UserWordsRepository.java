package com.yang.english.dao;
import com.yang.english.entiy.Cet4Word;
import com.yang.english.entiy.UserWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserWordsRepository extends JpaRepository<Cet4Word, Long> {


    @Query(value = "SELECT w.* FROM UserWords uw JOIN Cet4 w ON uw.word_id = w.word_id WHERE uw.user_id = ?1 AND uw.remembered = false ORDER BY RAND() LIMIT 80", nativeQuery = true)
    List<Cet4Word> findUnrememberedWordsByUserId(Long userId);

    @Query(value = "SELECT w.* FROM UserWords uw JOIN Cet4 w ON uw.word_id = w.word_id WHERE uw.user_id = ?1 AND uw.remembered = true ORDER BY RAND() LIMIT 210", nativeQuery = true)
    List<Cet4Word> findRememberedWordsByUserId(Long userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE userwords SET remembered = true WHERE user_id = :userId AND word_id = :wordId", nativeQuery = true)
    void markWordAsRemembered(@Param("userId") Long userId, @Param("wordId") Long wordId);
}
