package com.yang.english.dao;




import com.yang.english.entiy.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AudioRepository extends JpaRepository<Audio, Long> {
    List<Audio> findByListeningBookId(Long listeningBookId);
}
