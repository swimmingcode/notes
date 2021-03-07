package org.youyuan.jwt.utils.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Describe: 发送邮箱工具类
 * @Author: youjiancheng
 * @date 2021/2/17 11:05
 */
@Component
public class EmailUtils {

    @Autowired
    JavaMailSender javaMailSender;

    /**
     * 发送html模板
     * */
    public void sendHtmlMail(String from,String to,String subject,String content){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content,true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
