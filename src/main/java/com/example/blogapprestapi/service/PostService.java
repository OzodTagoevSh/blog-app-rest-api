package com.example.blogapprestapi.service;

import com.example.blogapprestapi.exception.ResourceNotFound;
import com.example.blogapprestapi.payload.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPosts();
    PostDto createPost(PostDto postDto);
    PostDto getPostById(Long postId) throws ResourceNotFound;
    PostDto updatePost(PostDto postDto, Long postId);
    void deletePostById(Long postId);
}
