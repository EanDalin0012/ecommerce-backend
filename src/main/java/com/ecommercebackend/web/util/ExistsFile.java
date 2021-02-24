package com.ecommercebackend.web.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExistsFile {

    public static boolean exists(String resourcePath) {
        Path firstPath = Paths.get(resourcePath);
        return Files.exists(firstPath);
    }
}
