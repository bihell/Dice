package com.bihell.dice.service;

import com.bihell.dice.model.domain.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void sendEmailToAdmin() throws InterruptedException {
        Comment comment = new Comment();
        comment.setName("tpxcer");
        comment.setContent("测试邮件内容");
        comment.setArticleId(1);
        comment.setEmail("tpxcer@outlook.com");

        emailService.sendEmailToAdmin(comment);
        Thread.sleep(2000);
    }
}
