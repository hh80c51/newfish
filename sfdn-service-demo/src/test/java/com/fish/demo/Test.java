package com.fish.demo;

import com.fish.demo.bean.Book;

import java.util.Objects;

/**
 * @author hh
 * @description
 * @date 2020/11/16  21:19
 */
public class Test {

    @org.junit.Test
    public void validateTest(){
        Book book = null;
        Objects.requireNonNull(book);

    }
}
