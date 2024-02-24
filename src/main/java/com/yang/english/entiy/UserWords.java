package com.yang.english.entiy;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name="user_table")
@Data
public class UserWords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_word_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "word_id")
    private Cet4Word word;
    private Boolean remembered;
    private Date last_reviewed;
    private int review_count;

}
