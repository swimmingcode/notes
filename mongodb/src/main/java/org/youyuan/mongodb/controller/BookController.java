package org.youyuan.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youyuan.mongodb.bean.Book;
import org.youyuan.mongodb.repository.BookDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/8 22:13
 */
@RestController
public class BookController {

    @Autowired
    BookDao bookDao;

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/test1")
    public void test1() {
        List<Book> books = new ArrayList<>();
        Book book1 = Book.builder().id(1).author("zs").name("java").build();
        Book book2 = Book.builder().id(2).author("lisi").name("web").build();
        Book book3 = Book.builder().id(3).author("ww").name("MySQL").build();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        bookDao.insert(books);

        //查询
        List<Book> res1 = bookDao.findByAuthorContains("zs");
        System.out.println(res1);
        System.out.println("----------------------------");
        Book res2 = bookDao.findByNameEquals("java");
        System.out.println(res2);
    }

    @GetMapping("/test2")
    public void test2() {
        List<Book> books = new ArrayList<>();
        Book book1 = Book.builder().id(4).author("zs4").name("java1").build();
        Book book2 = Book.builder().id(5).author("lisi5").name("web1").build();
        Book book3 = Book.builder().id(6).author("ww26").name("MySQL1").build();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        mongoTemplate.insertAll(books);

        List<Book> all = mongoTemplate.findAll(Book.class);
        System.out.println(all);
        Book byId = mongoTemplate.findById(4, Book.class);
        System.out.println(byId);
    }



}
