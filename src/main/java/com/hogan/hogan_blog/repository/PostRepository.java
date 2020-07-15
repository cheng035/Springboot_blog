package com.hogan.hogan_blog.repository;

import com.hogan.hogan_blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}
