package com.microservice.comment.service;

import com.microservice.comment.config.RestTemplateConfig;
import com.microservice.comment.entity.Comment;
import com.microservice.comment.payload.Post;
import com.microservice.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RestTemplateConfig restTemplate;

    public Comment saveComment(Comment comment){
        try {
            Post post = restTemplate.getRestTemplate().getForObject("http://localhost:8080/api/posts/" + comment.getPostId(), Post.class);

            if (post != null) {
                String commentId = UUID.randomUUID().toString();
                comment.setCommentId(commentId);
                Comment savedComment = commentRepository.save(comment);
                return savedComment;
            } else {
                return null;
            }
        } catch (Exception e) {
            // Log the exception for further analysis
            e.printStackTrace();
            return null;
        }
    }
}
