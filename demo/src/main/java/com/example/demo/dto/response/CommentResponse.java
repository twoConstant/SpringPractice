package com.example.demo.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponse {
    private Long commentId;
    private String content;
    private Long userId;
    private Long articleId;

    @Builder
    public CommentResponse(Long commentId, String content, Long userId, Long articleId) {
        this.commentId = commentId;
        this.content = content;
        this.userId = userId;
        this.articleId = articleId;
    }


}
