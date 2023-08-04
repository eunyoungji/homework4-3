package com.example.homework4.service;


import com.example.homework4.dto.PostRequestDto;
import com.example.homework4.dto.PostResponseDto;
import com.example.homework4.entity.Post;
import com.example.homework4.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        Post savePost = postRepository.save(post);
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;

    }

    public List<PostResponseDto> readPost() {
        return postRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public List<PostResponseDto> getPostByKeyword(String keyword) {
        return postRepository.findAllByContentsContainsOrderByModifiedAtDesc(keyword).stream().map(PostResponseDto::new).toList();
    }

    @Transactional
    public Long updatePost(Long id, PostRequestDto requestDto) {
        Post post = findPost(id);
        post.update(requestDto);
        return id;
    }

    public Long deletePost(Long id) {
        Post post = findPost(id);
        postRepository.delete(post);
        return id;
    }

    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않는다 게로!.")
        );
    }

}
