package com.yash.jdbcclientdemo.Post;

import java.time.LocalDate;

public record Post(int id, String title, String slug, LocalDate date, int timeToRead, String tags) {
}

