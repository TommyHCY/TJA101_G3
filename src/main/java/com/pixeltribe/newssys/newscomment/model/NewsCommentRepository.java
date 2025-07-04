package com.pixeltribe.newssys.newscomment.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsCommentRepository extends JpaRepository<NewsComment, Integer> {

    @Query("""
            select new com.pixeltribe.newssys.newscomment.model.NewsCommentDTO( n.id,n.ncomCon,n.ncomCre,n.ncomStatus,n.memNo.id,n.memNo.memNickName,n.ncomLikeLc,n.ncomLikeDlc) from NewsComment n where n.newsNo.id=:newsNo order by n.ncomCre
            """)
    public List<NewsCommentDTO> getNewsCommentsByNewsNo(Integer newsNo);

    public NewsComment save(NewsComment newsComment);
}