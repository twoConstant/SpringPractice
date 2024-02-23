package com.example.demo.controller;

import com.example.demo.dto.request.CommentRequest;
import com.example.demo.dto.response.CommentResponse;
import com.example.demo.service.CommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // 특정 게시글의 특정 유저가 쓴 전체 댓글 조회
    @GetMapping("/users/{user_id}/articles/{article_id}/comments")
    public ResponseEntity<List<CommentResponse>> findAllArticleUserComment(
            @PathVariable("user_id") Long userId,
            @PathVariable("article_id") Long articleId) {
        List<CommentResponse> commentResponseList = commentService.findAllArticleUserComment(userId, articleId);
        return ResponseEntity.ok(commentResponseList);
    }

    // 특정 게시글의 전체 댓글 조회
    @GetMapping("/articles/{article_id}/comments")
    public ResponseEntity<List<CommentResponse>> findAllArticleComment(@PathVariable("article_id") Long article_id) {
        List<CommentResponse> commentResponseList = commentService.findAllArticleComment(article_id);
        return ResponseEntity.ok(commentResponseList);
    }

    // 댓글 생성
    @Transactional
    @PostMapping("/users/{user_id}/articles/{article_id}/comments")
    public ResponseEntity<CommentResponse> saveComment(
            @PathVariable("user_id") Long userId,
            @PathVariable("article_id") Long articleId,
            @RequestBody CommentRequest request
            ) {
        CommentResponse commentResponse = commentService.saveComment(userId, articleId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentResponse);
    }
//
//
//    // 댓글 수정
    @Transactional
    @PutMapping("/comments/{comment_id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable("comment_id") Long commentId, @RequestBody CommentRequest request) {
        CommentResponse commentResponse = commentService.updateComment(commentId, request);
        return ResponseEntity.ok(commentResponse);
    }
//
//
//    // 댓글 삭제
    @Transactional
    @DeleteMapping("/comments/{comment_id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("comment_id") Long comment_id) {
        commentService.deleteComment(comment_id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

}
