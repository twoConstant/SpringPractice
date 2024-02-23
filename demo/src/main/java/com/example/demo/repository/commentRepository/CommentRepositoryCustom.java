package com.example.demo.repository.commentRepository;

import com.example.demo.entity.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    // 특정 게시물에서 특정 유저가 쓴 모든 댓글 조회
    // 유저id, 게시글 id가 필요
    List<Comment> findAllArticleUserComment(Long userId, Long articleId);

    // 해당 게시글에 작성된 모든 댓글 조회
    // 게시글 id 필요
    List<Comment> findAllArticleComment(Long articleId);
}
