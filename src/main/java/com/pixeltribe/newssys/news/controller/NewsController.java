package com.pixeltribe.newssys.news.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.pixeltribe.common.PageResponse;
import com.pixeltribe.newssys.news.model.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/News")
public class NewsController {

    private final NewsService newsSrv;


    public NewsController(NewsService newsSrv) {
        this.newsSrv = newsSrv;
    }

    @GetMapping("/search")
    @Operation(summary = "搜尋前台新聞")
    public List<NewsAdminDTO> search(
            @RequestParam String  keyword){
                return newsSrv.search(keyword);
    }

    @GetMapping("all")
    @Operation(summary = "顯示所有前台新聞")
    public PageResponse<NewsDTO> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return newsSrv.findAll(page, size);
    }

    @GetMapping("{newsId}")
    @Operation(summary = "顯示某一則新聞")
    public NewsDTO findById(@PathVariable Integer newsId) {
        return newsSrv.getOneNews(newsId);
    }

    @GetMapping("admin/newscount")
    @Operation(summary = "取得新聞數量")
    public Long getNewsCount() {
        return newsSrv.getNewsCount();
    }

    @GetMapping("admin/{newsId}")
    @Operation(summary = "後台查詢某一則新聞")
    public NewsAdminDTO findOneAdmin(@PathVariable Integer newsId) {
        return newsSrv.findOneAdmin(newsId);
    }

    @GetMapping("admin/allNews")
    @Operation(summary = "顯示所有新聞")
    public PageResponse<NewsAdminDTO> findNewsAdminPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword) {

        return newsSrv.findAllAdminNews(keyword, page, size);
    }

    @PostMapping("admin/create")
    @Operation(summary = "新增新聞")
    public NewsCreationDTO creationNews(@Valid @RequestBody NewsCreationDTO nCDTO) {
        return newsSrv.createNews(nCDTO.getNewsTit(), nCDTO.getNewsCon(), nCDTO.getAdminNo(), nCDTO.getTags());
    }

    @PatchMapping("admin/update/{id}")
    @Operation(summary = "修改某則新聞")
    public NewsAdminUpdateDto updateNews(
            @PathVariable Integer id,
            @Valid @RequestBody NewsAdminUpdateDto nauDTO) {

        nauDTO.setId(id);
        return newsSrv.updateNews(nauDTO);
    }

    @PostMapping("admin/redis/create")
    @Operation(summary = "新增新聞")
    public ResponseEntity<?> createNews(@Valid @RequestBody NewsCreationDTO dto,
                                        HttpServletRequest req) {
        try {
            return newsSrv.createAdmin(dto, req);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("admin/updateShowStatus/{id}")
    @Operation(summary = "更新新聞顯示狀態")
    public NewsAdminUpdateDto updateShowStatus(
            @PathVariable Integer id,
            @RequestBody Map<String, Boolean> requestBody) {
        Boolean isShowed = requestBody.get("isShowed");
        return newsSrv.updateShowStatus(id, isShowed);
    }

}
