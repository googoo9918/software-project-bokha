package com.app.domain.post.controller;

import com.app.domain.common.MultiResponseDto;
import com.app.domain.common.SingleResponseDto;
import com.app.domain.member.constant.Role;
import com.app.domain.member.service.MemberService;
import com.app.domain.post.dto.PostDto;
import com.app.domain.post.entity.Post;
import com.app.domain.post.mapper.PostMapper;
import com.app.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@Validated
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final MemberService memberService;

    private final PostMapper mapper;

    //글 등록
    @PostMapping("/post")
    public ResponseEntity createPost(@Valid @RequestBody PostDto.Post post,
                                     HttpServletRequest request){

        Post createPost = postService.createPost(mapper.postDtoToPost(post), memberService.getLoginMember(request));
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.postToPostResponseDto(createPost)), HttpStatus.CREATED);
    }

    //글 수정
    @PatchMapping("/edit/{post-id}")
    public ResponseEntity updatePost(@PathVariable("post-id") @Positive long postId,
                                     @Valid @RequestBody PostDto.Patch patch,
                                     HttpServletRequest httpServletRequest){
        patch.setPostId(postId);

        Post post = postService.updatePost(mapper.patchDtoToPost(patch));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.postToPostResponseDto(post)), HttpStatus.OK);
    }

    //글 상세 조회
    @GetMapping("/{post-id}")
    public ResponseEntity findPost(@PathVariable("post-id") @Positive long postId){
        Post findPost = postService.find(postId);
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.postToPostResponseDto(findPost)), HttpStatus.OK);
    }

    //FAQ 전체 조회
    @GetMapping("/faq-list")
    public ResponseEntity findAllFaqPost(@Positive @RequestParam(defaultValue = "1") int page,
                                      @Positive @RequestParam(defaultValue = "10") int size) {
        Page<Post> pagePost = postService.findAllFaq(page-1, size);
        List<Post> posts = pagePost.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.postsToPostResponseDtos(posts), pagePost), HttpStatus.OK);
    }

    // 공지사항 전체 조회
    @GetMapping("/notice-list")
    public ResponseEntity findAllNoticePost(@Positive @RequestParam(defaultValue = "1") int page,
                                      @Positive @RequestParam(defaultValue = "10") int size) {
        Page<Post> pagePost = postService.findAllNotice(page-1, size);
        List<Post> posts = pagePost.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.postsToPostResponseDtos(posts), pagePost), HttpStatus.OK);
    }
    //글 삭제
    @DeleteMapping("/delete/{post-id}")
    public ResponseEntity deletePost(@PathVariable("post-id")@Positive Long id){
        postService.deletePost(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
