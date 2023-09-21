package com.yash.jdbcclientdemo;

import com.yash.jdbcclientdemo.Post.Post;
import com.yash.jdbcclientdemo.Post.PostServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;

@SpringBootApplication
public class JdbcClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcClientDemoApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(@Qualifier("clientPostService") PostServices postService) {
		return args -> {
			postService.create(new Post(0,"Hello World", "hello-world", LocalDate.now(), 1, "java, spring"));
		};
	}
}
