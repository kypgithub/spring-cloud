package com.abugong.cloud.service;

import javax.mail.MessagingException;

import com.abugong.cloud.utils.R;

public interface MailService {

	R textMail();

	R enclosureMail() throws MessagingException;

	R enclosureEmbedMail() throws MessagingException;
}
