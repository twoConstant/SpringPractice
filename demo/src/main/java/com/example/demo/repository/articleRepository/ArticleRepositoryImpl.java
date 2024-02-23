package com.example.demo.repository.articleRepository;

import com.example.demo.entity.Article;
import com.example.demo.entity.QArticle;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Article> findAllArticleUserByUserId(Long userId) {
        QArticle a = QArticle.article;

        return jpaQueryFactory
                .select(a)
                .from(a)
                .where(a.user.id.eq(userId))
                .fetch();
    }
}
