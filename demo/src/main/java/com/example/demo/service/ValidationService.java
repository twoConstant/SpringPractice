package com.example.demo.service;

import com.example.demo.repository.articleRepository.ArticleRepository;
import com.example.demo.repository.commentRepository.CommentRepository;
import com.example.demo.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ValidationService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    // 404 Not Found
    public boolean isValidUserId(Long userId) {
        return userRepository.existsById(userId) ;
    }

    public boolean isValidArticleId(Long articleId) {
        return articleRepository.existsById(articleId) ;
    }

    public boolean isValidCommentId(Long commentId) {
        return commentRepository.existsById(commentId) ;
    }
}
