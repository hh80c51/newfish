package com.fish.demo.bean;

import com.fish.demo.enums.Topic;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

/**
 * @author hh
 * @description
 * @date 2020/11/15  22:20
 */
@Data
public class Book {
    public static com.fish.demo.enums.Topic Topic;
    private String title;
    private List<String> authors;
    private int[] pageCounts;
    private Topic topic;
    private Year pubDate;
    private double height;
    private BigDecimal price;

    public Book(String title, List<String> authors, int[] pageCounts,  Year pubDate, double height, Topic topic) {
        this.title = title;
        this.authors = authors;
        this.pageCounts = pageCounts;
        this.topic = topic;
        this.pubDate = pubDate;
        this.height = height;
    }

    public Book() {

    }
}
