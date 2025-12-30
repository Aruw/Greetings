package com.aruw.greetings.util;

import java.io.IOException;
import java.nio.file.Files;
import org.springframework.core.io.ClassPathResource;

public class HtmlLoader {

    public static String loadHtmlFile() {
        ClassPathResource resource = new ClassPathResource("templates/email-template.html");
        try {
            return new String(Files.readAllBytes(resource.getFile().toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}