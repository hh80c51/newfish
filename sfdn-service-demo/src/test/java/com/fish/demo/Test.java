package com.fish.demo;

import com.fish.demo.bean.Book;
import lombok.Data;

import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.springframework.util.ClassUtils.isPresent;

/**
 * @author hh
 * @description
 * @date 2020/11/16  21:19
 */
@Data
public class Test {

    private Test2 test2;
    private Test3 test3;

    @org.junit.Test
    public void validateTest(){
        Book book = null;
        Objects.requireNonNull(book);

    }

    public String testSimple(Test test) {
        if (test == null) {
            return "";
        }
        if (test.getTest3() == null) {
            return "";
        }
        if (test.getTest3().getTest2() == null) {
            return "";
        }
        if (test.getTest3().getTest2().getInfo() == null) {
            return "";
        }
        return test.getTest3().getTest2().getInfo();
    }

//    public String testOptional(Test test) {
//        return Optional.ofNullable(test).flatMap(Test::getTest3)
//                .flatMap(Test3::getTest2)
//                .map(Test2::getInfo)
//                .orElse("");
//    }

/*
    Optional.ofNullable(param).orElse(new Param());
    Optional的初始方式有3个方法，分别是：

    ofNullable：允许一个可能为空的对象

    of：需要一个不为空的对象

    这里需要自行判断，初始化的对象是否必然非空。

    Optional获取对应值并进行判空的方法有以下几种：

    orElse：如果值为空，返回一个对象

    orElseGet：如果值为空，执行一段lambda并返回一个对象

    orElseThrow：如果值为空，抛出一个异常

    */

}
