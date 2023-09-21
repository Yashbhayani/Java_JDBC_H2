package com.yash.jdbcclientdemo.Post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ClientPostService implements PostServices{
    private static final Logger log = LoggerFactory.getLogger(ClientPostService.class);
    private final JdbcClient jdbcClient;

    public ClientPostService(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Post> findAll() {
        return jdbcClient.sql("SELECT id,title,slug,date,time_to_read,tags FROM post")
                .query(Post.class)
                .list();
    }

    @Override
    public Optional<Post> findById(int id) {
        return jdbcClient.sql("SELECT id,title,slug,date,time_to_read,tags FROM post WHERE id = :id")
                .param("id", id)
                .query(Post.class)
                .optional();
    }

    @Override
    public void create(Post post) {
        var updated = jdbcClient.sql("INSERT INTO post(title,slug,date,time_to_read,tags) values(?,?,?,?,?)")
                .params(List.of(post.title(),post.slug(),post.date(),post.timeToRead(),post.tags()))
                .update();

        Assert.state(updated == 1, "Failed to create post " + post.title());
    }

    @Override
    public void update(Post post, int id) {
        var updated = jdbcClient.sql("update post set title = ?, slug = ?, date = ?, time_to_read = ?, tags = ? where id = ?")
                .params(List.of(post.title(), post.slug(), post.date(), post.timeToRead(), post.tags(), id))
                .update();

        Assert.state(updated == 1, "Failed to update post " + post.title());
    }


    @Override
    public void delete(int id) {
        var updated = jdbcClient.sql("delete from post where id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete post " + id);
    }
}
