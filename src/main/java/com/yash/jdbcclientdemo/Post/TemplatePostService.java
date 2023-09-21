package com.yash.jdbcclientdemo.Post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TemplatePostService implements PostServices{
    private static final Logger log = LoggerFactory.getLogger(TemplatePostService.class);
    private final JdbcTemplate jdbcTemplate;

    public TemplatePostService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<Post> rowMapper = (rs, rowNum) -> new Post(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("slug"),
            rs.getDate("date").toLocalDate(),
            rs.getInt("time_to_read"),
            rs.getString("tags")
    );

    public List<Post> findAll() {
        var sql = "SELECT id,title,slug,date,time_to_read,tags FROM post";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Optional<Post> findById(int id) {
        var sql = "SELECT id,title,slug,date,time_to_read,tags FROM post WHERE id = ?";
        Post post = null;
        try {
            post = jdbcTemplate.queryForObject(sql,rowMapper,id);
        } catch (DataAccessException ex) {
            log.info("Post not found: " + id);
        }

        return Optional.ofNullable(post);
    }

    public void create(Post post) {
        String sql = "INSERT INTO post(title,slug,date,time_to_read,tags) values(?,?,?,?,?)";
        int insert = jdbcTemplate.update(sql,post.title(),post.slug(),post.date(),post.timeToRead(),post.tags());
        if(insert == 1) {
            log.info("New Post Created: " + post.title());
        }
    }

    public void update(Post post, int id) {
        String sql = "update post set title = ?, slug = ?, date = ?, time_to_read = ?, tags = ? where id = ?";
        int update = jdbcTemplate.update(sql,post.title(),post.slug(),post.date(),post.timeToRead(),post.tags(),id);
        if(update == 1) {
            log.info("Post Updated: " + post.title());
        }
    }

    public void delete(int id) {
        String sql = "delete from post where id = ?";
        int delete = jdbcTemplate.update(sql,id);
        if(delete == 1) {
            log.info("Post Deleted: " + id);
        }
    }
}
