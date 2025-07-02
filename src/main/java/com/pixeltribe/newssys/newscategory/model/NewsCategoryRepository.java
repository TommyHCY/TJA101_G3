package com.pixeltribe.newssys.newscategory.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsCategoryRepository extends JpaRepository<NewsCategory, Integer> {
}
