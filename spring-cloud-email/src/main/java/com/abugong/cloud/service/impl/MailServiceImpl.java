package com.abugong.cloud.service.impl;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.abugong.cloud.service.MailService;
import com.abugong.cloud.utils.R;

@Service
public class MailServiceImpl implements MailService{
	@Autowired
	private JavaMailSender JavaMailSender;

	/**
	 * 邮件
	 */
	@Override
	public R textMail() {
		SimpleMailMessage mimeMessage = new SimpleMailMessage();
		mimeMessage.setFrom("kangpingbj@163.com");
		mimeMessage.setTo("646918332@qq.com");
		mimeMessage.setSubject("邮件测试");
		mimeMessage.setText("看就看绿军绿绿绿绿绿绿绿绿");
		JavaMailSender.send(mimeMessage);
		return R.ok();
	}
	/**
	 * 附件的邮件
	 * @throws MessagingException 
	 */
	@Override
	public R enclosureMail() throws MessagingException {
		MimeMessage mimeMessage = JavaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("kangpingbj@163.com");
		helper.setTo("646918332@qq.com");
		helper.setSubject("邮件测试");
		helper.setText("<html><body><img src='cid:tupian'/></body></html>", true);
		
		FileSystemResource file = new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\20180424214700_RTChl.png"));
		helper.addAttachment("图片.jpg", file);
		JavaMailSender.send(mimeMessage);
		return R.ok();
	}
	
	/**
	 * 附件嵌入正文
	 * @throws MessagingException 
	 */
	@Override
	public R enclosureEmbedMail() throws MessagingException {
		MimeMessage mimeMessage = JavaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("kangpingbj@163.com");
		helper.setTo("646918332@qq.com");
		helper.setSubject("邮件测试");
		helper.setText("<html><body><img src='cid:tupian'/></body></html>", true);
		
		FileSystemResource file = new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\20180424214700_RTChl.png"));
		helper.addInline("tupian", file);
		JavaMailSender.send(mimeMessage);
		return R.ok();
	}

}
