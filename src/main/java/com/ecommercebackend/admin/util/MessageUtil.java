package com.ecommercebackend.admin.util;

import com.ecommercebackend.core.component.Translator;
import com.ecommercebackend.core.constant.ErrorCode;
import com.ecommercebackend.core.dto.ErrorMessage;

public class MessageUtil {
    public static String message(String key, String lang) {
        String message = Translator.translate(lang, key);
        if (ErrorCode.EXCEPTION_ERR == key) {
            message = Translator.translate(lang, key);
        } else if ("status".equals(key)) {
            message = Translator.translate(lang, "status");
        } else if ("user_id".equals(key)) {
            message = Translator.translate(lang, "user_id");
        }

        return message;
    }

    public static String message(String key, String lang, String cMessage) {
        String message = Translator.translate(lang, key);
        if (ErrorCode.EXCEPTION_ERR == key) {
            message = Translator.translate(lang, key);
        } else if ("status".equals(key)) {
            message = Translator.translate(lang, "status");
        } else if ("user_id".equals(key)) {
            message = Translator.translate(lang, "user_id");
        }
        if (cMessage.equals("Access is denied")) {
            message = Translator.translate(lang, "Access_Denied");
        }

        return message;
    }
}
