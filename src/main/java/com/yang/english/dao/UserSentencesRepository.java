package com.yang.english.dao;

import com.yang.english.entiy.Sentence;
import com.yang.english.entiy.SentenceWithUserInputDTO;
import com.yang.english.entiy.UserSentences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserSentencesRepository extends JpaRepository<Sentence, Long> {
    @Query(value = "SELECT s.*,  COALESCE(us.user_input, '') AS user_input  FROM Sentences s  LEFT JOIN user_sentences us ON s.audio_id = us.audio_id AND s.id = us.sentences_id  WHERE s.audio_id = :audioId AND us.user_id = :userId ",nativeQuery = true)
    List<Object> findUserSentencesByUserIdAndAudioId(@Param("userId") int userId, @Param("audioId") int audioId);
}
