package com.pixeltribe.forumsys.entity;

import com.pixeltribe.forumsys.message.model.ForumMes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import com.pixeltribe.membersys.member.model.Member;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "article_com_report")
public class ArticleComReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NO", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MES_NO")
    private ForumMes mesNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTER")
    private Member reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RPI_NO")
    private ReportType rpiNo;

    @NotNull
    @ColumnDefault("'0'")
    @Column(name = "ART_COM_REP_STATUS", nullable = false)
    private Character artComRepStatus;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "CREATE_TIME")
    private Instant createTime;

    @Column(name = "FINISH_TIME")
    private Instant finishTime;

}