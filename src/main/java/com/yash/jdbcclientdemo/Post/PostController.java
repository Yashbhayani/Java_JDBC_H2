package com.yash.jdbcclientdemo.Post;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Qualifier("clientPostService")
    private  final  PostServices postServices;

    public  PostController( @Qualifier("clientPostService") PostServices postService){
        this.postServices = postService;
    }

    @GetMapping("")
    List<Post> findAll(){
        return  postServices.findAll();
    }

    @GetMapping("/{id}")
    Optional<Post> findById(@PathVariable int id){
        return  postServices.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody Post post){
        postServices.create(post);
    }

    @PutMapping("/{id}")
    void update(@RequestBody Post post, @PathVariable int id){
        postServices.update(post, id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
          postServices.delete(id);
    }
}
