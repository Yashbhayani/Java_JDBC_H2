package com.yash.jdbcclientdemo.Post;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface PostServices {

    List<Post> findAll();
    Optional<Post> findById(int id);
    void create(Post post);
    void update(Post post, int id);
    void delete(int id);
}
