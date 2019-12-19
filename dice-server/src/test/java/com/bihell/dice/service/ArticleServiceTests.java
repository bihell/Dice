package com.bihell.dice.service;

import com.bihell.dice.model.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        System.out.println(articleService.getFrontArticle(1));
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
}
