package com.blog.blogapi.service;

import com.blog.blogapi.dto.PostRequest;
import com.blog.blogapi.entity.Post;
import com.blog.blogapi.entity.User;
import com.blog.blogapi.repository.PostRepository;
import com.blog.blogapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post createPost(PostRequest request, String username) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthor(author);

        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public Post updatePost(Long id, PostRequest request, String username) {
        Post post = getPostById(id);

        if (!post.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("You are not authorized to update this post");
        }

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        return postRepository.save(post);
    }

    public void deletePost(Long id, String username) {
        Post post = getPostById(id);

        if (!post.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("You are not authorized to delete this post");
        }

        postRepository.delete(post);
    }
}
