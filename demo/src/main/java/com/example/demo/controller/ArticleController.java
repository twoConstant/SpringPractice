package com.example.demo.controller;

import com.example.demo.dto.request.ArticleRequest;
import com.example.demo.dto.response.ArticleResponse;
import com.example.demo.dto.response.OneArticleResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import com.example.demo.service.ArticleService;
import com.example.demo.service.UserService;
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
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;

    // 게시글 단일 조회
    @GetMapping("/articles/{article_id}")
    @Operation(summary = "게시글 단일 조회", description = "게시글 ID를 통해 특정 게시글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 게시글을 찾았을 때", content = @Content(schema = @Schema(implementation = Article.class)))
    @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없을 때")
    public ResponseEntity<ArticleResponse> findArticleById(@PathVariable("article_id") Long articleId) {
        ArticleResponse articleResponse = articleService.findArticleById(articleId);
        return ResponseEntity.ok(articleResponse);
    }

    // 게시글 전체 조회
    @GetMapping("/articles")
    @Operation(summary = "게시글 전체 조회", description = "모든 게시글을 조회 합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 모든 게시글을 찾았을 때", content = @Content(schema = @Schema(implementation = Article.class)))
    @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없을 때")
    public ResponseEntity<List<OneArticleResponse>> findAllArticles() {
        List<OneArticleResponse> allArticles = articleService.findAllArticle();
        return ResponseEntity.ok(allArticles);
    }


    // 특정 유저가 쓴 게시글 전체 조회
    @GetMapping("/users/{user_id}/articles")
    public ResponseEntity<List<OneArticleResponse>> findAllArticleByUserId(@PathVariable("user_id") Long userId) {
        List<OneArticleResponse> articleResponseList = articleService.findAllArticleByUserId(userId);
        return ResponseEntity.ok(articleResponseList);
    }


    // 게시글 단일 생성
    @PostMapping("/users/{user_id}/articles")
    @Transactional
    @Operation(summary = "게시글 단일 생성", description = "새로운 게시글을 생성합니다.")
    @ApiResponse(responseCode = "201", description = "성공적으로 게시글을 생성했을 때", content = @Content(schema = @Schema(implementation = Article.class)))
    public ResponseEntity<ArticleResponse>  addArticle(@PathVariable Long user_id , @RequestBody ArticleRequest request) {
        ArticleResponse articleResponse = articleService.addArticle(request, user_id);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(articleResponse);
    }

    // 게시글 단일 삭제
    @DeleteMapping("/article/{article_id}")
    @Transactional
    @Operation(summary = "게시글 단일 삭제", description = "게시글 ID를 통해 특정 게시글을 삭제합니다.")
    @ApiResponse(responseCode = "204", description = "성공적으로 게시글을 삭제했을 때")
    public ResponseEntity<Void>  deleteArticle(@PathVariable Long article_id) {
       articleService.deleteArticleById(article_id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    // 게시글 단일 수정
    @PutMapping("/users/{user_id}/articles/{article_id}")
    @Transactional
    @Operation(summary = "게시글 단일 수정", description = "게시글 ID를 통해 특정 게시글을 수정합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 게시글을 수정했을 때", content = @Content(schema = @Schema(implementation = Article.class)))
    public ResponseEntity<ArticleResponse> updateArticleById(@PathVariable("user_id") Long userId, @PathVariable("article_id") Long articleId, @RequestBody ArticleRequest request) {
        UserResponse userResponse = userService.findUserById(userId);
        ArticleResponse updatedArticle = articleService.updateArticle(articleId, request, userResponse);

        return ResponseEntity.ok(updatedArticle);
    }
}
