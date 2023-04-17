package com.example.blogapprestapi.service.imp;

import com.example.blogapprestapi.exception.ResourceNotFound;
import com.example.blogapprestapi.model.Post;
import com.example.blogapprestapi.payload.PostDto;
import com.example.blogapprestapi.repository.PostRepository;
import com.example.blogapprestapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImp implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostServiceImp(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .toList();
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) throws ResourceNotFound {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFound("Post", "Id", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFound("Post", "Id", postId));

        post.setTitle(postDto.getTitle());
        post.setDescription(post.getDescription());
        post.setContent(post.getContent());
        postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void deletePostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFound("Post", "Id", postId));
        postRepository.delete(post);
    }
}
