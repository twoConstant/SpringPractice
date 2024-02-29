package com.example.demo.service;

import com.example.demo.dto.request.ArticleRequest;
import com.example.demo.dto.response.ArticleResponse;
import com.example.demo.dto.response.OneArticleResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import com.example.demo.repository.articleRepository.ArticleRepository;
import com.example.demo.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ValidationService validationService;

    // 게시글 단일 조회
    public ArticleResponse findArticleById(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("No Article with Request article_id"));
        return ArticleResponse.builder()
                .name(article.getUser().getName())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }

    // 게시글 전체 조회
    public List<OneArticleResponse> findAllArticle() {
        return articleRepository.findAll()
                .stream()
                .map((a) -> OneArticleResponse.builder()
                        .id(a.getId())
                        .title(a.getTitle())
                        .content(a.getContent())
                        .build())
                .toList();
    }

    public List<OneArticleResponse> findAllArticleByUserId(Long userId) {
        List<Article> articleList = articleRepository.findAllArticleUserByUserId(userId);
        return articleList
                .stream()
                .map((article -> OneArticleResponse.builder()
                        .id(article.getId())
                        .title(article.getTitle())
                        .content(article.getContent())
                        .build()
                ))
                .toList();
    }

    // 게시글 단일 생성
    public ArticleResponse addArticle(ArticleRequest request, Long user_id) {
        // user 찾기
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new IllegalArgumentException("There is no user with your Request user_id"));
        // Article 객체로 만들고, 저장해서 Article 객체 반환.
        Article article = articleRepository.save(request.toArticle(user));
        return ArticleResponse.builder()
                .name(user.getName())
                .title(article.getTitle())
                .content(article.getContent())
                .build();

    }

    // 게시글 단일 삭제
    public void deleteArticleById(Long article_id) {
        if (validationService.isValidArticleId(article_id)) {
            articleRepository.deleteById(article_id);
            return;
        }
        throw new IllegalArgumentException("there is no Article with your request articleId");
    }


    // 게시글 단일 수정
    public ArticleResponse updateArticle(Long article_id, ArticleRequest request, UserResponse userResponse) {
        // 수정할 게시글 찾고
        Article findArticle = articleRepository.findById(article_id)
                .orElseThrow(() -> new IllegalArgumentException("No Article with Request article_id"));
        // 해당 엔티티를 request의 내용으로 바꾸고 다시 저장? ==> Article에 update 메서드를 추가해서 해결
        // 변경사항을 JPA가 알아차리고 알아서 최신화해줌
        findArticle.update(
                request.getTitle(),
                request.getContent()
        );
        return ArticleResponse.builder()
                .name(userResponse.getName())
                .title(findArticle.getTitle())
                .content(findArticle.getContent())
                .build();


    }

    public Page<ArticleResponse> getArticles(Pageable pageable) {
        Page<Article> articles = articleRepository.findAll(pageable);
        return articles.map(article -> new ArticleResponse().builder()
                .title(article.getTitle())
                .content(article.getContent())
                .name(article.getUser().getName())
                .build());
    }
}
