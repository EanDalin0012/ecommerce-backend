package com.ecommercebackend.core.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Translator {

    private static ReloadableResourceBundleMessageSource translatorSource;

    @Autowired
    Translator(ReloadableResourceBundleMessageSource messageSource) {
        Translator.translatorSource = messageSource;
    }

    public static String translate(String languageCode, String msgCode) {
        String language = "en";
        if (languageCode == "kh") {
            language = "kh";
        } else if (languageCode == "05") {
            language = "ch";
        }
        Locale locale = new Locale(languageCode);
        return translatorSource.getMessage(msgCode, null, locale);
    }
}
