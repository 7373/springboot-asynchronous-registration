package com.example.demo.mailUtils;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Collection;
import java.util.Properties;


/**
 * @author https://www.cnblogs.com/xmqa/p/8458300.html
 * @description
 * @when 2019-09-01
 */
public class SendQQMailUtil {

    protected static Properties config = null;


    public static void sendQQmail(String title, String body,String address) {
        // 得到回话对象
        Session session = Session.getInstance(config);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        try {
            // 设置发件人邮箱地址
            message.setFrom(new InternetAddress("79996793@qq.com"));
            // 设置收件人邮箱地址
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(address.trim())});
            // 设置邮件标题
            message.setSubject(title);
            // 设置邮件内容
            message.setText(body);
            // 得到邮差对象
            Transport transport = session.getTransport();
            // 连接自己的邮箱账户
            // 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
            transport.connect("7", "qtgnjngevvebgdj");
            // 发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void sendQQmail(String title, String body, Collection<String> address) {
        Session session = Session.getInstance(config);
        Message message = new MimeMessage(session);
        try {
            // 设置发件人邮箱地址
            message.setFrom(new InternetAddress(""));
            // 设置收件人邮箱地址

            message.setRecipients(Message.RecipientType.TO, (InternetAddress[]) address.parallelStream().map(e ->
            {
                try {
                    return new InternetAddress(e);
                } catch (AddressException e1) {
                    e1.printStackTrace();
                }
                return null;
            }).toArray());
            // 设置邮件标题
            message.setSubject(title);
            // 设置邮件内容
            message.setText(body);
            // 得到邮差对象
            Transport transport = session.getTransport();
            // 连接自己的邮箱账户
            // 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
            transport.connect("", "qtegnjvgdj");
            // 发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    static {
        config = new Properties();
        // 连接协议
        config.put("mail.transport.protocol", "smtp");
        // 主机名
        config.put("mail.smtp.host", "smtp.q.com");
        // 端口号
        config.put("mail.smtp.port", 46);
        config.put("mail.smtp.auth", "true");
        // 设置是否使用ssl安全连接 ---一般都使用
        config.put("mail.smtp.ssl.enable", "true");
        // 设置是否显示debug信息 true 会在控制台显示相关信息
        config.put("mail.debug", "false");
    }
}
