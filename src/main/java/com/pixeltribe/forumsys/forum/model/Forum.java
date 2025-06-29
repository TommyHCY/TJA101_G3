package com.pixeltribe.forumsys.forum.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pixeltribe.forumsys.forumVO.ForumChatMessage;
import com.pixeltribe.forumsys.forumVO.ForumCollect;
import com.pixeltribe.forumsys.forumVO.ForumLike;
import com.pixeltribe.forumsys.forumVO.ForumPost;
import com.pixeltribe.forumsys.forumcategory.model.ForumCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "forum")
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOR_NO", nullable = false)
    private Integer id;

    @Size(max = 30)
    @NotEmpty(message="討論區名稱: 請勿空白")
    @Column(name = "FOR_NAME", nullable = false, length = 30)
    private String forName;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "CAT_NO")
    @JsonBackReference
    private ForumCategory catNo;

    @Size(max = 255)
    @Column(name = "FOR_IMG_URL")
    private String forImgUrl;

    @JsonProperty("categoryName")
    public String CategoryName() {
        if (this.catNo != null) {
            return this.catNo.getCatName();
        }
        return null;
    }

    @Transient
    private Integer categoryId;

    @Size(max = 255)
    @NotEmpty(message="討論區描述: 請勿空白")
    @Column(name = "FOR_DES")
    private String forDes;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "FOR_DATE", insertable = false, updatable = false)
    private Instant forDate;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "FOR_UPDATE", insertable = false, updatable = false)
    private Instant forUpdate;

    @NotNull
    @ColumnDefault("'0'")
    @Column(name = "FOR_STATUS", nullable = false)
    private Character forStatus;



    @OneToMany(mappedBy = "forNo")
    @JsonIgnore
    private Set<ForumChatMessage> forumChatMessages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "forNo")
    @JsonIgnore
    private Set<ForumCollect> forumCollects = new LinkedHashSet<>();

    @OneToMany(mappedBy = "forNo")
    @JsonIgnore
    private Set<ForumLike> forumLikes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "forNo")
    @JsonIgnore
    private Set<ForumPost> forumPosts = new LinkedHashSet<>();



}