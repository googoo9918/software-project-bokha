package com.app.domain.post.mapper;

import com.app.domain.post.dto.PostDto;
import com.app.domain.post.entity.Post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post postDtoToPost(PostDto.Post post);

    Post patchDtoToPost(PostDto.Patch patch);

    default PostDto.Response postToPostResponseDto(Post post){
        return PostDto.Response.of(post);
    }

    List<PostDto.Response> postsToPostResponseDtos(List<Post> posts);
}
