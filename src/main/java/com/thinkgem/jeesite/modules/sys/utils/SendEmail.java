package com.thinkgem.jeesite.modules.sys.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.utils.PropertiesLoader;

@Service
@Lazy(false)
@Transactional(readOnly = true)
public class SendEmail {

	private static Logger logger = Logger.getLogger(SendEmail.class);
	private static PropertiesLoader loader = new PropertiesLoader("email.properties");
	private static final Map<String, String> mailConf = new HashMap<String, String>();

//	public static void main(String args[]){
//		String a = "clientId=1&timeStamp="+ new Date().getTime()+"&email=6563@qq.com";
//		String url = "http://localhost:8182/dengyutest/domainname/activateEmail.html?params=" + Encodes.encodeBase64(a);
//		String b [] = a.split("&");
//		System.out.println(url);
//		System.out.println(b[1]);
//		System.out.println(b[2]);
//	}
	/**
	 * @param to 邮件发送接收者地址
	 * @param text1 发送的文本，为null则会从配置文件读默认模板
	 * @param req 请求 从模板里读文本时需要使用
	 * @param clientId 从模板里读文本时需要使用，请求发送激活链接的会员id
	 */
	public static void sendEmail(String to,String text1,HttpServletRequest req,String params) throws Exception {
		try {
			
			String emailServerHost = null;//email主机
			String from = null;//发送方邮件地址
			String username = null;//发送方邮件用户名
			String password = null;//发送方邮件密码
			String text = null;//模板 发送的文本格式信息
			String subject = null;//邮件主题
			String mailSmtpAuth = null;//
			String mailSmtpTimeout = null;//
			String emailServerPort = null;//email主机 端口号
			String ssl = "false";
			
			if (!mailConf.containsKey("emailServerPort")) {
				mailConf.put("emailServerPort", loader.getProperty("emailServerPort"));
				emailServerPort = mailConf.get("emailServerPort");
			}else{
				emailServerPort = mailConf.get("emailServerPort");
			}
			if (!mailConf.containsKey("emailServerHost")) {
				mailConf.put("emailServerHost", loader.getProperty("emailServerHost"));
				emailServerHost = mailConf.get("emailServerHost");
			}else{
				emailServerHost = mailConf.get("emailServerHost");
			}
			
			if (mailConf.containsKey("emailSSL")) {
				ssl = mailConf.get("emailSSL");
			}
			
			if (!mailConf.containsKey("from")) {
				mailConf.put("from", loader.getProperty("from"));
				from = mailConf.get("from");
			}else{
				from = mailConf.get("from");
			}
			
			if (!mailConf.containsKey("username")) {
				mailConf.put("username", loader.getProperty("username"));
				username = mailConf.get("username");
			}{
				username = mailConf.get("username");
			}
			
			if (!mailConf.containsKey("password")) {
				mailConf.put("password", loader.getProperty("password"));
				password = mailConf.get("password");
			}else{
				password = mailConf.get("password");
			}
			
			if (!mailConf.containsKey("text")) {
				mailConf.put("text", loader.getProperty("text"));
				text = mailConf.get("text");
				String url = null;
				String ServerPort = String.valueOf(req.getServerPort());
				if (ServerPort.equalsIgnoreCase("80")) {
					url = req.getScheme() + "://" + req.getServerName() + req.getContextPath() + "/domainname/activateEmail.html?params="+params;
				} else {
					url = req.getScheme() + "://" + req.getServerName() + ":" + String.valueOf(req.getServerPort())+req.getContextPath() + "/domainname/activateEmail.html?params="+params;
				}
			
				java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//处理日期格式
				String a = req.getServletPath();
				text = text.replace("email", to);
				text = text.replace("http", url);
				text = text.replace("Date1", format.format(new Date()));
				//设置操作限制时间
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				cal.add(Calendar.DAY_OF_MONTH, 2);//把日期从当前往后增加两天.整数往后推,负数往前移动
				text = text.replace("Date2", format.format(cal.getTime()));
			}else{
				text = mailConf.get("text");
				String url = null;
				String ServerPort = String.valueOf(req.getServerPort());
				if (ServerPort.equalsIgnoreCase("80")){
					url = req.getScheme() + "://" + req.getServerName() + req.getContextPath()+"/domainname/activateEmail.html?params="+params;
				}else{
					url = req.getScheme() + "://" + req.getServerName() + ":" + String.valueOf(req.getServerPort())+req.getContextPath()+"/domainname/activateEmail.html?params="+params;
				}
				java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//处理日期格式
				text = text.replace("email", to);
				text = text.replace("http", url);
				text = text.replace("Date1", format.format(new Date()));
				//设置操作限制时间
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				cal.add(Calendar.DAY_OF_MONTH, 2);//把日期从当前往后增加两天.整数往后推,负数往前移动
				text = text.replace("Date2", format.format(cal.getTime()));
			}
			
			if (!mailConf.containsKey("subject")) {
				mailConf.put("subject", loader.getProperty("subject"));
				subject = mailConf.get("subject");
			}else{
				subject = mailConf.get("subject");
			}
			
			if (!mailConf.containsKey("mailSmtpAuth")) {
				mailConf.put("mailSmtpAuth", loader.getProperty("mailSmtpAuth"));
				mailSmtpAuth = mailConf.get("mailSmtpAuth");
			}else{
				mailSmtpAuth = mailConf.get("mailSmtpAuth");
			}
			
			if (!mailConf.containsKey("mailSmtpTimeout")) {
				mailConf.put("mailSmtpTimeout", loader.getProperty("mailSmtpTimeout"));
				mailSmtpTimeout = mailConf.get("mailSmtpTimeout");
			}else{
				mailSmtpTimeout = mailConf.get("mailSmtpTimeout");
			}
			
			JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

			// 设定mail server host
			senderImpl.setHost(emailServerHost);
			//设置端口号
			senderImpl.setPort(Integer.parseInt(emailServerPort));

			// 建立邮件消息,发送简单邮件和html邮件的区别
			MimeMessage mailMessage = senderImpl.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, "UTF-8");

			// 设置收件人，寄件人
			messageHelper.setTo(to);
			messageHelper.setFrom(from);
			messageHelper.setSubject(subject);
			// true 表示启动HTML格式的邮件
			if(text1==null){
				messageHelper.setText(text,true);
			}else{
				messageHelper.setText(text1,true);
			}
			logger.debug("邮件内容：\n" + text);
			senderImpl.setUsername(username); // 根据自己的情况,设置username
			senderImpl.setPassword(password); // 根据自己的情况, 设置password
			Properties prop = new Properties();
			prop.put("mail.smtp.auth",mailSmtpAuth); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
			prop.put("mail.smtp.timeout", mailSmtpTimeout);
			if ("true".equals(ssl)) {
				prop.put("mail.smtp.ssl.enable", true);
				prop.put("mail.transport.protocol", "smtps");
			}
			senderImpl.setJavaMailProperties(prop);
			// 发送邮件
			senderImpl.send(mailMessage);
		} catch (MailException e) {
			logger.error("发送邮件失败", e);
			throw new Exception("邮件地址非法", e);
		} catch (MessagingException e) {
			logger.error("发送邮件失败", e);
			throw new Exception("邮件地址非法", e);
		}
	}
}

