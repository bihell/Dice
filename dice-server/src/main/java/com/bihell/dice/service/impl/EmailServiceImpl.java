package com.bihell.dice.service.impl;

import com.bihell.dice.model.domain.Comment;
import com.bihell.dice.service.EmailService;
import com.bihell.dice.service.LogService;
import com.bihell.dice.service.OptionService;
import com.bihell.dice.util.DiceConsts;
import com.bihell.dice.util.OptionKeys;
import com.bihell.dice.util.Types;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * 发送邮件 Service 实现类
 *
 * @author bihell
 * @since 2018/4/9 15:52
 */
@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EmailServiceImpl implements EmailService {

    private final OptionService optionService;

    private final LogService logService;

    private final TemplateEngine templateEngine;

    @Override
    @Async
    public void sendEmailToAdmin(Comment comment) {
        if (!isEmail(comment.getEmail())) {
            return;
        }

        Context context = getEmailContext(comment);
        String content = templateEngine.process("mail_admin", context);

        String logData = content + ";  发送给管理员";
        log.info("sendEmailToAdmin start: {}", new Date().toString());
        try {
            String emailUsername = optionService.get(OptionKeys.EMAIL_USERNAME);
            sendEmail(content, emailUsername);
            logService.save(Types.LOG_ACTION_SEND_EMAIL, logData, Types.LOG_MESSAGE_SEND_EMAIL_SUCCESS, Types.LOG_TYPE_EMAIL);
        } catch (Exception e) {
            logService.save(Types.LOG_ACTION_SEND_EMAIL, logData, Types.LOG_MESSAGE_SEND_EMAIL_FAIL, Types.LOG_TYPE_EMAIL);
            log.error(e.getMessage());
        }
    }

    @Override
    @Async
    public void sendEmailToUser(Comment comment, String replyEmail) {
        if (!isEmail(replyEmail)) {
            return;
        }

        Context context = getEmailContext(comment);
        String content = templateEngine.process("mail_user", context);

        String logData = content + ";  发送给:" + replyEmail;
        log.info("sendEmailToUser start: {}", new Date().toString());
        try {
            sendEmail(content, replyEmail);
            logService.save(Types.LOG_ACTION_SEND_EMAIL, logData, Types.LOG_MESSAGE_SEND_EMAIL_SUCCESS, Types.LOG_TYPE_EMAIL);
        } catch (Exception e) {
            logService.save(Types.LOG_ACTION_SEND_EMAIL, logData, Types.LOG_MESSAGE_SEND_EMAIL_FAIL, Types.LOG_TYPE_EMAIL);
            log.error(e.getMessage());
        }
    }

    /**
     * 判定是否要发送该邮件
     *
     * @param email 收件人邮箱
     * @return 是否发送邮件
     */
    private boolean isEmail(String email) {
        boolean isEmail = optionService.get(OptionKeys.IS_EMAIL, Boolean.FALSE);
        if (!isEmail) {
            return false;
        }

        String adminUserEmail = optionService.get(OptionKeys.EMAIL_USERNAME, "");
        // 如果是管理员的回复则不必通知管理员
        return StringUtils.isEmpty(adminUserEmail) || !adminUserEmail.equals(email);
    }

    /**
     * 获取邮件Context
     *
     * @param comment 评论
     * @return {@see Context}
     */
    private Context getEmailContext(Comment comment) {
        Context context = new Context();

        String websiteName = optionService.get(OptionKeys.BLOG_NAME);
        String website = optionService.get(OptionKeys.BLOG_WEBSITE);

        // 如果网址最后没有/,则补上
        if (!StringUtils.isEmpty(website)
                && website.lastIndexOf("/") != website.length()) {
            website = website + "/";
        }

        context.setVariable("websiteName", websiteName);
        context.setVariable("website", website);
        context.setVariable("name", comment.getName());
        context.setVariable("content", comment.getContent());
        context.setVariable("articleId", String.valueOf(comment.getArticleId()));
        return context;
    }

    /**
     * 发送邮件
     *
     * @param content 邮件内容(html)
     * @param to      收件人
     * @throws MessagingException
     */
    private void sendEmail(String content, String to) throws MessagingException {
        String subject = optionService.get(OptionKeys.EMAIL_SUBJECT, DiceConsts.EMAIL_TEMPLATE_DEFAULT_SUBJECT);
        String host = optionService.get(OptionKeys.EMAIL_HOST);
        Integer port = optionService.get(OptionKeys.EMAIL_PORT, 25);
        String username = optionService.get(OptionKeys.EMAIL_USERNAME);
        String password = optionService.get(OptionKeys.EMAIL_PASSWORD);

        JavaMailSender mailSender = (JavaMailSender) mailSender(host, port,
                username, password);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setFrom(username);
        helper.setTo(to);
        helper.setText(content, true);
        helper.setSubject(subject);
        mailSender.send(mimeMessage);
    }


    /**
     * 获取MailSender
     *
     * @param host     主机名
     * @param port     端口
     * @param username 邮件名
     * @param password 密码
     * @return MailSender
     */
    private MailSender mailSender(String host, Integer port, String username, String password) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        return mailSender;
    }
}
