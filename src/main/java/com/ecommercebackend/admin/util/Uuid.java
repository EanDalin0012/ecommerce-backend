package com.ecommercebackend.admin.util;



import com.ecommercebackend.core.util.SystemDateUtil;

import java.util.UUID;

public class Uuid {
    public static String randomUUID() {
        UUID uuid = UUID.randomUUID();
        String id = uuid + "-" + SystemDateUtil.getDateFormat("yyyyMMdd-hhmmss");
        return id;
    }
}
