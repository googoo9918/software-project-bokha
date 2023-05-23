package com.app.domain.post.dto;

import com.app.domain.post.entity.Post;
import lombok.*;

public class PostDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post{
        private String title;
        private String content;
        private com.app.domain.post.entity.Post.Kind kind;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch{
        private long postId;

        private String title;

        private String content;

        private com.app.domain.post.entity.Post.Kind kinds;

        public void setPostId(long postId) {
            this.postId = postId;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        private long postId;
        private String title;
        private String content;
        private com.app.domain.post.entity.Post.Kind kind;
        private int views;
        private String writer;
        public static Response of(com.app.domain.post.entity.Post post) {
            return Response.builder()
                    .postId(post.getPostId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .kind(post.getKind())
                    .views(post.getViews())
                    .writer(post.getMember().getMemberName())
                    .build();
        }
    }
}
