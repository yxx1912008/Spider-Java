package cn.luckydeer.spider.common.utils.email;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import cn.luckydeer.spider.common.utils.thread.ExecutorServiceUtils;

/**
 * 阿里云邮件发送
 * 
 * @author yuanxx
 * @version $Id: AliyunEmail.java, v 0.1 2018年9月27日 上午11:01:41 yuanxx Exp $
 */
public class AliyunEmail {

    /** 日志. */
    private static final Log          logger = LogFactory.getLog(AliyunEmail.class);

    private static JavaMailSenderImpl mailSender;

    static {
        mailSender = new JavaMailSenderImpl();
    }

    private static JavaMailSenderImpl getMailSender() {
        if (null == mailSender) {
            synchronized (AliyunEmail.class) {
                if (null == mailSender) {
                    mailSender = new JavaMailSenderImpl();
                }
            }
        }
        return mailSender;
    }

    /**
     * 异步发送邮箱
     * @param emailOrder
     */
    public static void send(final EmailOrder emailOrder) {
        ExecutorServiceUtils.getExcutorPools().execute(new Runnable() {
            public void run() { // 实现抽象方法run()  
                boolean flag = sendMail(emailOrder);
                if (flag && null != emailOrder.getFile() && emailOrder.getFile().isFile()) {
                    try {
                        emailOrder.getFile().delete();
                    } catch (Exception e) {
                        logger.error(emailOrder.getFile().getName() + ":删除临时文件异常", e);
                    }
                }
            }
        });
    }

    public static boolean sendMail(EmailOrder emailOrder) {
        try {
            mailSender = getMailSender();
            // 发送者认证权限
            mailSender.setUsername(emailOrder.getUserName()); // 根据自己的情况,设置发送者
            mailSender.setPassword(emailOrder.getPassword()); // 根据自己的情况, 设置password

            Properties prop = new Properties();

            prop.put("mail.smtp.host", emailOrder.getHost());
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.put("mail.smtp.socketFactory.fallback", "false");
            prop.put("mail.smtp.port", "465");
            prop.put("mail.smtp.socketFactory.port", "465");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.timeout", "25000");

            mailSender.setJavaMailProperties(prop);

            //建立邮件消息,发送简单邮件和html邮件的区别 
            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");

            // 发送人邮箱
            messageHelper.setFrom(emailOrder.getSender());
            // 设置收件人 邮箱
            messageHelper.setTo(emailOrder.getReceives());

            // 主题
            messageHelper.setSubject(emailOrder.getTitle());
            // true 表示启动HTML格式的邮件 
            messageHelper.setText(emailOrder.getContent(), true);
            // 加入附件
            if (null != emailOrder.getFile() && emailOrder.getFile().isFile()) {
                messageHelper.addAttachment(emailOrder.getFile().getName(), emailOrder.getFile());
            }
            mailSender.send(mailMessage); //发送邮件 
            return true;
        } catch (Exception e) {
            logger.error("email发送异常:" + emailOrder.getTitle(), e);
        }
        return false;
    }

}
