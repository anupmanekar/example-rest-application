package com.mandisoft.services.member.utility;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component("localeMessageUtility")
public class LocaleMessageUtility implements MessageSourceAware {

	private MessageSource messageService;

	@Override
	public void setMessageSource(final MessageSource messageSource) {
		this.messageService = messageSource;

	}

	/**
	 * For given key it will load message from property files
	 * 
	 * @param key
	 * @return {@link String}
	 */
	public String getMessage(final String key) {
		final String msg = this.messageService.getMessage(key, null, "unknown", Locale.US);
		return msg;
	}

	/**
	 * For given key it will load message from property files, also replacing
	 * the properties
	 * 
	 * @param key
	 * @param params
	 * @return {@link String}
	 */
	public String getMessage(final String key, final Object[] params) {
		final String msg = this.messageService.getMessage(key, params, "unknown", Locale.US);
		return msg;
	}

	/**
	 * For given error code it will load message from property files
	 * 
	 * @param key
	 * @return {@link String}
	 */
	public String getErrorMessage(final int key) {
		final String errorMsg = this.messageService.getMessage(key + "", null, "unknown", Locale.US);
		return errorMsg;
	}

}
