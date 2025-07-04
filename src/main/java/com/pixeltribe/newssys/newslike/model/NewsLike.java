package com.pixeltribe.newssys.newslike.model;

import com.pixeltribe.membersys.member.model.Member;
import com.pixeltribe.newssys.newscomment.model.NewsComment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "news_like")
public class NewsLike {
    @Id
    @Column(name = "NLIKE_NO", nullable = false)
    private Integer id;

    @NotNull
    @ColumnDefault("'1'")
    @Column(name = "NLIKE_STATUS", nullable = false)
    private Character nlikeStatus;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEM_NO", nullable = false)
    private Member memNo;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "NLIKE_UPDATE", nullable = false)
    private Instant nlikeUpdate;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "NLIKE_CRDATE", nullable = false)
    private Instant nlikeCrdate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NCOM_NO", nullable = false)
    private NewsComment ncomNo;

}