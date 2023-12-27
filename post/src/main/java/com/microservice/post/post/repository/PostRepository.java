package com.microservice.post.post.repository;

import com.microservice.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, String> {


}
