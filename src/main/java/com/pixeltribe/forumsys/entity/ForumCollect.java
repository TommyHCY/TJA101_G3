package com.pixeltribe.forumsys.entity;

import com.pixeltribe.forumsys.forum.model.Forum;
import com.pixeltribe.membersys.member.model.Member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "forum_collect")
public class ForumCollect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FCOLL_NO", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOR_NO")
    private Forum forNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEM_NO")
    private Member memNo;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "FCOLL_UPDATE")
    private Instant fcollUpdate;

}