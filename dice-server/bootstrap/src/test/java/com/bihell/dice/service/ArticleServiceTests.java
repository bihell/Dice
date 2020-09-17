package com.bihell.dice.service;

import com.bihell.dice.blog.model.blog.Article;
import com.bihell.dice.blog.service.blog.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTests {

    @Autowired
    ArticleService articleService;

    @Test
    public void getFrontArticles() {
        System.out.println(articleService.getFrontArticles(1, 20));

    }

    @Test
    public void getFrontArticle() {
        System.out.println(articleService.getFrontArticle(1,"aa"));
    }

    @Test
    public void deleteArticle() {
        articleService.deleteArticle(15);
    }

    @Test
    public void count() {
        System.out.println(articleService.count());
    }

    @Test
    public void saveArticle(){
        Article article = new Article().selectById(15);
        articleService.saveArticle(article);
    }
    @Test
    public void getFrontPage(){
        System.out.println(articleService.getFrontPage(6));
    }

    @Test
    // 查询链式调用
    public void chain(){
        List<Article> articleList = articleService.lambdaQuery().eq(Article::getId,1).list();
        articleList.forEach(System.out::println);
    }

    @Test
    // 更新链式调用
    public void chainUpdate(){
        articleService.lambdaUpdate().eq(Article::getId,1).set(Article::getId,1).update();
    }

    @Test
    // 链式调用删除
    public void chainDelete(){
        articleService.lambdaUpdate().eq(Article::getId,999).remove();
    }
}
