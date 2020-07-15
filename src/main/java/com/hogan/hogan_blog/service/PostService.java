package com.hogan.hogan_blog.service;

import com.hogan.hogan_blog.dto.PostDto;
import com.hogan.hogan_blog.exception.PostNotFoundException;
import com.hogan.hogan_blog.model.Post;
import com.hogan.hogan_blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {
    @Autowired
    private AuthService authService;
    @Autowired
    PostRepository postRepository;
    public void createPost(PostDto postDto){

        Post post = mapFromDtoToPost(postDto);
        postRepository.save(post);
    }

    public List<PostDto> showAllPosts() {
        List<Post> posts=postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(toList());

    }

    private PostDto mapFromPostToDto(Post post){
        PostDto postDto=new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getUserName());
        return postDto;
    }

    private Post mapFromDtoToPost(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        User user = authService.getCurrentUser().orElseThrow(()->new IllegalArgumentException("no user logged in "));
        post.setUserName(user.getUsername());
        post.setCreatedOn(Instant.now());
        post.setUpdateOn(Instant.now());
        return post;
    }
    public PostDto findPostById(Long id){
        Post post=postRepository.findById(id).orElseThrow(()->new PostNotFoundException("For id"+id));
        return mapFromPostToDto(post);
    }
}
