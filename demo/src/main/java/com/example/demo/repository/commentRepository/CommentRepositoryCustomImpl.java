package com.example.demo.repository.commentRepository;

import com.example.demo.entity.Comment;
import com.example.demo.entity.QComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    private static final QComment comment = QComment.comment;
    @Override
    public List<Comment> findAllArticleUserComment(Long userId, Long articleId) {
        return jpaQueryFactory
                .select(comment)
                .from(comment)
                .where(comment.article.id.eq(articleId)
                        .and(comment.user.id.eq(userId)))
                .fetch();
    }

    @Override
    public List<Comment> findAllArticleComment(Long articleId) {
        return jpaQueryFactory
                .select(comment)
                .from(comment)
                .where(comment.article.id.eq(articleId))
                .fetch();
    }
}
