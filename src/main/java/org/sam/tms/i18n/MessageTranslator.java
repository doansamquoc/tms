package org.sam.tms.i18n;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageTranslator {
	MessageSource messageSource;
	Locale locale = LocaleContextHolder.getLocale();

	public String of(String code, Object[] args) {
		return messageSource.getMessage(code, args, locale);
	}

	public String of(String code) {
		return messageSource.getMessage(code, null, locale);
	}
}
