package com.abugong.cloud.controller;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.abugong.cloud.service.MailService;
import com.abugong.cloud.utils.R;

@RestController
@RequestMapping("/mail")
public class MailController {
	private Logger logger = LoggerFactory.getLogger(MailController.class);
	@Autowired
    private MailService mailService;
  
	@PostMapping("/textMail")
	public R textMail() {
		return mailService.textMail();
	}
	@PostMapping("/enclosureMail")
	public R enclosureMail() throws MessagingException {
		return mailService.enclosureMail();
	}
	
	@PostMapping("/enclosureEmbedMail")
	public R enclosureEmbedMail() throws MessagingException {
		return mailService.enclosureEmbedMail();
	}
}
