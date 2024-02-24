package com.yang.english.dao;
import com.yang.english.entiy.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SentenceRepository extends JpaRepository<Sentence, Integer> {
    List<Sentence> findByAudioId(Integer audioId);
}
