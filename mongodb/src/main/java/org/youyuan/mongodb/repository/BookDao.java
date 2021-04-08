package org.youyuan.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.youyuan.mongodb.bean.Book;

import java.util.List;

/**
 * @Describe: 动态代理生成
 * @Author: youjiancheng
 * @date 2021/4/8 22:08
 */
public interface BookDao extends MongoRepository<Book,Integer> {

    List<Book> findByAuthorContains(String author);

    Book findByNameEquals(String name);
}
