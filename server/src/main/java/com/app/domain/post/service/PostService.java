package com.app.domain.post.service;

import com.app.domain.member.entity.Member;
import com.app.domain.member.service.MemberService;
import com.app.domain.post.entity.Post;
import com.app.global.error.ErrorCode;
import com.app.domain.post.repository.PostRepository;
import com.app.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final MemberService memberService;

    public Post createPost(Post post, Member member){
        post.setMember(member);
        return postRepository.save(post);
    }

    public Post updatePost(Post post){
        Post findPost = findVerifiedPost(post.getPostId());

        Optional.ofNullable(post.getTitle())
                .ifPresent(title -> findPost.setTitle(title));

        Optional.ofNullable(post.getContent())
                .ifPresent(content -> findPost.setContent(content));

        Optional.ofNullable(post.getKind())
                .ifPresent(kind -> findPost.setKind(kind));

        return postRepository.save(findPost);
    }

    public Post find(long postId){
        Post post = findVerifiedPost(postId);
        updateViews(post);
        return post;
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllFaq(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("postId").descending());

        Page<Post> pagePost = postRepository.findAllByKind(Post.Kind.FAQ,pageable);

        List<Post> listPost = pagePost.getContent();

        return pagePost;
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllNotice(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("postId").descending());

        Page<Post> pagePost = postRepository.findAllByKind(Post.Kind.NOTICE, pageable);

        List<Post> listPost = pagePost.getContent();

        return pagePost;
    }

    public void deletePost(Long id){
        Post post = findVerifiedPost(id);

        postRepository.delete(post);
    }
    /**
     * 글 검증 조회
     * @param postId
     * @return
     */
    private Post findVerifiedPost(long postId) {
        Optional<Post> optPost = Optional.of(postRepository.getReferenceById(postId));
        Post findPost = optPost.orElseThrow(() -> new EntityNotFoundException(ErrorCode.POST_NOT_EXISTS));

        return findPost;
    }


    /**
     * 조회수 증가 로직
     */
    private void updateViews(Post post) {

        int findViews = post.getViews() + 1;

        post.setViews(findViews);

        postRepository.save(post);
    }
}
