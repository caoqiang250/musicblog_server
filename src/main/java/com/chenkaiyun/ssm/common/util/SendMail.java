package com.chenkaiyun.ssm.common.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendMail {
	@Autowired
    private JavaMailSender mailSender; //框架自带的
	
	
	@Value("${spring.mail.username}")
    private String username;
	
	//发送HTML邮件
    private boolean htmlMail(String to, String subject, String html){
    	System.out.println(username);
        boolean result = true;
        MimeMessage mailMessage = mailSender.createMimeMessage();  
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);  

        try {
            // 设置收件人，寄件人 用数组发送多个邮件
            messageHelper.setTo(to);
            messageHelper.setFrom(username);
            messageHelper.setSubject(subject);
            // true 表示启动HTML格式的邮件  
            messageHelper.setText(html, true);  

            // 发送邮件
            mailSender.send(mailMessage);
        } catch (MessagingException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }
    
}
