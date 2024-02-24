package com.yang.english.entiy;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "audio")
@Data
public class Audio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false)
    private Long weigh;

    @Column(name = "create_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    private String origin;

    @Column(name = "delete_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteTime;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "file_id", nullable = false)
    private Integer fileId;

    @Column(name = "audio_url_cdn", nullable = false, columnDefinition = "TEXT")
    private String audioUrlCdn;

    // getters and setters
    @ManyToOne
    @JoinColumn(name = "listening_book_id", referencedColumnName = "id")
    private ListeningBook listeningBook;
}
