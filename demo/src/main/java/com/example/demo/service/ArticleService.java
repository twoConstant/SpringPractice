package com.example.demo.service;

import com.example.demo.dto.request.ArticleRequest;
import com.example.demo.dto.response.ArticleResponse;
import com.example.demo.dto.response.OneArticleResponse;
import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository blogRepository;
    private final UserRepository userRepository;

    // 게시글 단일 조회
    public Article findArticleById(Long articleId) {
        return blogRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("No Article with Request article_id"));
    }

    // 게시글 전체 조회
    public List<OneArticleResponse> findAllArticle() {
        return blogRepository.findAll()
                .stream()
                .map((a) -> OneArticleResponse.builder()
                        .id(a.getId())
                        .title(a.getTitle())
                        .content(a.getContent())
                        .build())
                .toList();
    }

    // 게시글 단일 생성
    public Article addArticle(ArticleRequest request, Long user_id) {
        // user 찾기
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new IllegalArgumentException("There is no user with your Request user_id"));
        // Article 객체로 만들고, 저장해서 Article 객체 반환.
        return blogRepository.save(request.toArticle(user));
    }

    // 게시글 단일 삭제
    public void deleteArticleById(Long article_id) {
        blogRepository.deleteById(article_id);
    }



    // 게시글 단일 수정
    public Article updateArticle(Long article_id, ArticleRequest request) {
        // 수정할 게시글 찾고
        Article findArticle = blogRepository.findById(article_id)
                .orElseThrow(() -> new IllegalArgumentException("No Article with Request article_id"));
        // 해당 엔티티를 request의 내용으로 바꾸고 다시 저장? ==> Article에 update 메서드를 추가해서 해결
        // 변경사항을 JPA가 알아차리고 알아서 최신화해줌
        findArticle.update(
                request.getTitle(),
                request.getContent()
        );

        return findArticle;
    }
}