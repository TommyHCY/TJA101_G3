package com.pixeltribe.forumsys.forum.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumRepository extends JpaRepository<Forum, Integer> {

//    依最後更新時間排序
    List<Forum> findAllByOrderByForUpdateDesc();

    List<Forum> findByCatNo_Id(Integer catNo); // 方法名稱 findBy{欄位名稱} 是固定的，參數名稱可

}
