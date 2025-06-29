package com.pixeltribe.newssys.newscomment.controller;

import com.pixeltribe.newssys.newscomment.model.NewsComment;
import com.pixeltribe.newssys.newscomment.model.NewsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api")
class NewsCommentController {

    @Autowired
    NewsCommentService newsCommentService;

    @GetMapping("NewsComment")
    public List<NewsComment> findAll(){
        return newsCommentService.findAll();
    }
}
