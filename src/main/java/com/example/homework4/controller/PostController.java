package com.example.homework4.controller;

import com.example.homework4.dto.PostRequestDto;
import com.example.homework4.dto.PostResponseDto;
import com.example.homework4.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts/create")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @GetMapping("/posts/read")
    public List<PostResponseDto> readPost() {
        return postService.readPost();
    }

    @GetMapping("/posts/update/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return this.postService.updatePost(id, requestDto);
    }

    @DeleteMapping("/posts/delete/{id}")
    public Long deletePost(@PathVariable Long id) {
        return this.postService.deletePost(id);
    }

    @GetMapping("/posts/search/{keyword}")
    public List<PostResponseDto> getPostByKeyword(String keyword) {
        return postService.getPostByKeyword(keyword);
    }



}

