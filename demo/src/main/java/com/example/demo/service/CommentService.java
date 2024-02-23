package com.example.demo.service;

import com.example.demo.dto.request.CommentRequest;
import com.example.demo.dto.response.CommentResponse;
import com.example.demo.entity.Article;
import com.example.demo.entity.Comment;
import com.example.demo.entity.User;
import com.example.demo.repository.articleRepository.ArticleRepository;
import com.example.demo.repository.commentRepository.CommentRepository;
import com.example.demo.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ValidationService validationService;

    // 특정 게시글에 특정 유저가 작성한 모든 댓글 조회
    public List<CommentResponse> findAllArticleUserComment(Long userId, Long articleId) {
        if (!validationService.isValidUserId(userId)) {
            throw new IllegalArgumentException("존재하지 않는 userId입니다.");
        }

        if (!validationService.isValidArticleId(articleId)) {
            throw new IllegalArgumentException("존재하지 않는 articleId입니다.");
        }

        List<Comment> commentList = commentRepository.findAllArticleUserComment(userId, articleId);
        return commentList.stream()
                .map(comment -> CommentResponse.builder()
                        .commentId(comment.getId())
                        .content(comment.getContent())
                        .userId(comment.getUser().getId())
                        .articleId(comment.getArticle().getId())
                        .build())
                .toList();
    }

    // 특정 게시글의 모든 댓글 조회
    public List<CommentResponse> findAllArticleComment(Long articleId) {

        if (!validationService.isValidArticleId(articleId)) {
            throw new IllegalArgumentException("존재하지 않는 articleId입니다.");
        }

        List<Comment> commentList = commentRepository.findAllArticleComment(articleId);
        return commentList.stream()
                .map(comment -> CommentResponse.builder()
                        .commentId(comment.getId())
                        .content(comment.getContent())
                        .userId(comment.getUser().getId())
                        .articleId(comment.getArticle().getId())
                        .build())
                .toList();
    }


    // 댓글 생성
    public CommentResponse saveComment(Long userId, Long articleId, CommentRequest commentRequest) {
        // 유저 객체 찾고
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        // 게시글 객체 찾고
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        // 댓글 엔티티 만들어서 저장
        Comment comment = commentRequest.toComment(user, article, commentRequest.getContent());
        Comment savedComment = commentRepository.save(comment);
        // commentResponse DTO 만들기
        return CommentResponse.builder()
                .commentId(savedComment.getId())
                .content(savedComment.getContent())
                .userId(savedComment.getUser().getId())
                .articleId(savedComment.getArticle().getId())
                .build();
    }

    // 댓글 수정
    public CommentResponse updateComment(Long commentId, CommentRequest request){
        // comment 불러오고
        System.out.println("댓글 수정 서비스계층 입성");
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글은 존재하지 않습니다."));
        // comment.update 하고
        System.out.println("comment 찾았다.");
        comment.updateComment(request.getContent());
        // commentResponse 넘겨주기
        return CommentResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .userId(comment.getUser().getId())
                .articleId(comment.getArticle().getId())
                .build();
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        if (validationService.isValidCommentId(commentId)) {
           commentRepository.deleteById(commentId);
           return;
        }
        throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
    }



}
