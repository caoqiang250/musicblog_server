package com.chenkaiyun.ssm.common.util;

import java.io.File;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMailDemo {
	private static final String HOST = "smtp.exmail.qq.com";
    private static final String SMTP = "smtp";
    //private static final String HOST = "smtp.mxhichina.com";
    //private static final String SMTP = "smtp";
    private static final String USERNAME = "cocobit@fengke.club";
    private static final String PASSWORD = "bGKN7cB5rk5oZY8p";
    //private static final int PORT = 110;
    //private static final String USERNAME = "newsletter@ihoneycomb.net";
    //private static final String PASSWORD = "Newsletter2018";
    private static final int PORT = 465;//587/465
    private static final String DEFAULTENCODING = "UTF-8";
    
    private static JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

    private static Properties prop = new Properties();

    static{
        // 设定mail server
        senderImpl.setHost(HOST);
        senderImpl.setProtocol(SMTP);
        senderImpl.setUsername(USERNAME);
        senderImpl.setPassword(PASSWORD);
        senderImpl.setPort(PORT);
        senderImpl.setDefaultEncoding(DEFAULTENCODING);

        // 设定properties
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.timeout", "25000");
        //设置调试模式可以在控制台查看发送过程
        prop.put("mail.debug", "true");
        //设置加密发送SSL   使用465端口发送时使用
        prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        senderImpl.setJavaMailProperties(prop);
    }

    public static void main(String args[]) {
    	//System.out.println(11);
        // 设置收件人，寄件人 用数组发送多个邮件
//      String[] array = new String[] {"88888@qq.com","666666@qq.com","999999999@qq.com",USERNAME};
        String[] array = new String[] {"952631256@qq.com"};
        String subject = "Welcome to HoneyComb! Confirm your email address for added account  security!";

//        String filePath = "C:/Users/Administrator/Desktop/工作/图片/email.png";
        String html = "<p>Hello!</p><br><p>Welcome to the HoneyComb family! Please enter the following verification code to complete your register.</p><br><h1 style='color:red'>858126</h1><br><p>This code will expire in 10 minutes.</p><br> <p>Thanks for visiting HoneyComb!</p>";
        //boolean result = inlineFileMail(array, subject, html, filePath);
        boolean result = htmlMail(array, subject, html);
        

        if (result) {
            System.out.println("发送邮件成功。。。。");
        }

    }

    /**
     * 发送简单邮件
     * @param to 收件人邮箱
     * @param subject 主题
     * @param content 内容
     * @return
     */
    public static boolean singleMail(String to, String subject, String content){
        String[] array = new String[] {to};
        return singleMail(array, subject, content);
    }


    /**
     * 发送简单文本邮件
     * @param to 收件人邮箱数组
     * @param subject 主题
     * @param content 内容
     * @return
     */
    public static boolean singleMail(String[] to, String subject, String content){
        boolean result = true;

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人 用数组发送多个邮件
        mailMessage.setTo(to);
        mailMessage.setFrom(USERNAME);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        // 发送邮件
        try {
            senderImpl.send(mailMessage);
        } catch (MailException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }


    /**
     * 发送html邮件
     * @param to 收件人
     * @param subject 主题
     * @param html html代码
     * @return
     */
    public static boolean htmlMail(String[] to, String subject, String html){
        boolean result = true;

        MimeMessage mailMessage = senderImpl.createMimeMessage();  
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);  

        try {
            // 设置收件人，寄件人 用数组发送多个邮件
            messageHelper.setTo(to);
            messageHelper.setFrom(USERNAME);
            messageHelper.setSubject(subject);
            // true 表示启动HTML格式的邮件  
            messageHelper.setText(html, true);  

            // 发送邮件
            senderImpl.send(mailMessage);
        } catch (MessagingException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 发送内嵌图片的邮件   （cid:资源名）
     * @param to 收件人邮箱
     * @param subject 主题
     * @param html html代码
     * @param imgPath 图片路径
     * @return
     */
    public static boolean inlineFileMail(String[] to, String subject, String html, String filePath){
        boolean result = true;

        MimeMessage mailMessage = senderImpl.createMimeMessage();  
        try {
            //设置true开启嵌入图片的功能
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true);  
            // 设置收件人，寄件人 用数组发送多个邮件
            messageHelper.setTo(to);
            messageHelper.setFrom(USERNAME);
            messageHelper.setSubject(subject);
            // true 表示启动HTML格式的邮件  
            messageHelper.setText(html, true);  

            FileSystemResource file = new FileSystemResource(new File(filePath));  
            messageHelper.addInline(file.getFilename(), file);  

            // 发送邮件
            senderImpl.send(mailMessage);
        } catch (MessagingException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param html
     * @param filePath
     * @return
     */
    public static boolean attachedFileMail(String[] to, String subject, String html, String filePath){
        boolean result = true;

        MimeMessage mailMessage = senderImpl.createMimeMessage();  

        try {
            // multipart模式 为true时发送附件 可以设置html格式
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,"utf-8");  
            // 设置收件人，寄件人 用数组发送多个邮件
            messageHelper.setTo(to);
            messageHelper.setFrom(USERNAME);
            messageHelper.setSubject(subject);
            // true 表示启动HTML格式的邮件  
            messageHelper.setText(html, true);  

            FileSystemResource file = new FileSystemResource(new File(filePath));  
            // 这里的方法调用和插入图片是不同的。  
            messageHelper.addAttachment(file.getFilename(), file);

            // 发送邮件
            senderImpl.send(mailMessage);
        } catch (MessagingException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }
}
