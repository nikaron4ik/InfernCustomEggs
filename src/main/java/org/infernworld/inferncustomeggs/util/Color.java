package org.infernworld.inferncustomeggs.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Color {

    public static String hex(String message) {
        Pattern pattern = Pattern.compile("&#([A-Fa-f0-9]{6})");
        Matcher matcher = pattern.matcher(message);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String color = matcher.group(1);
            StringBuilder replacement = new StringBuilder("§x");
            for (char c : color.toCharArray()) {
                replacement.append('§').append(c);
            }
            matcher.appendReplacement(buffer, replacement.toString());
        }
        matcher.appendTail(buffer);
        return buffer.toString().replace("&", "§");
    }
    public static List<String> hexList(List<String> messages) {
        List<String> coloredMessages = new ArrayList<>();
        for (String message : messages) {
            coloredMessages.add(hex(message));
        }
        return coloredMessages;
    }
}
