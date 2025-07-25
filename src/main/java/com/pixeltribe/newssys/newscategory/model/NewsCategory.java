package com.pixeltribe.newssys.newscategory.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pixeltribe.newssys.newscontentclassification.model.NewsContentClassification;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "news_category")
public class NewsCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NCAT_NO", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "NCAT_NAME", nullable = false, length = 50)
    private String ncatName;

    @OneToMany(mappedBy = "ncatNo")
    @JsonBackReference
    private Set<NewsContentClassification> newContentClassifications = new LinkedHashSet<>();

}