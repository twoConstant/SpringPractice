package com.example.demo.controller;

import com.example.demo.dto.request.AddArticleRequest;
import com.example.demo.dto.request.UpdateArticleRequest;
import com.example.demo.dto.response.FindAllArticlesResponse;
import com.example.demo.entity.Article;
import com.example.demo.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BlogController {

    private final BlogService blogService;
    // 게시글 단일 조회
    @GetMapping("/article/{article_id}")
    @Operation(summary = "게시글 단일 조회", description = "게시글 ID를 통해 특정 게시글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 게시글을 찾았을 때", content = @Content(schema = @Schema(implementation = Article.class)))
    @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없을 때")
    public ResponseEntity<Article> findArticleById(@PathVariable Long article_id) {
        Article findArticle = blogService.findArticleById(article_id);
        return ResponseEntity.ok(findArticle);
    }

    // 게시글 전체 조회
    @GetMapping("/articles")
    @Operation(summary = "게시글 전체 조회", description = "모든 게시글을 조회 합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 모든 게시글을 찾았을 때", content = @Content(schema = @Schema(implementation = Article.class)))
    @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없을 때")
    public ResponseEntity<List<FindAllArticlesResponse>> findAllArticles() {
        List<FindAllArticlesResponse> allArticles = blogService.findAllArticle();
        return ResponseEntity.ok(allArticles);
    }

    // 게시글 단일 생성
    @PostMapping("/article")
    @Transactional
    @Operation(summary = "게시글 단일 생성", description = "새로운 게시글을 생성합니다.")
    @ApiResponse(responseCode = "201", description = "성공적으로 게시글을 생성했을 때", content = @Content(schema = @Schema(implementation = Article.class)))
    public ResponseEntity<Article>  addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.addArticle(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    // 게시글 단일 삭제
    @DeleteMapping("/article/{article_id}")
    @Transactional
    @Operation(summary = "게시글 단일 삭제", description = "게시글 ID를 통해 특정 게시글을 삭제합니다.")
    @ApiResponse(responseCode = "204", description = "성공적으로 게시글을 삭제했을 때")
    public ResponseEntity<Void>  deleteArticle(@PathVariable Long article_id) {
       blogService.deleteArticleById(article_id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    // 게시글 단일 수정
    @PutMapping("/article/{article_id}")
    @Transactional
    @Operation(summary = "게시글 단일 수정", description = "게시글 ID를 통해 특정 게시글을 수정합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 게시글을 수정했을 때", content = @Content(schema = @Schema(implementation = Article.class)))
    public ResponseEntity<Article> updateArticleById(@PathVariable Long article_id, @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.updateArticle(article_id, request);
        return ResponseEntity.ok(updatedArticle);
    }
}
