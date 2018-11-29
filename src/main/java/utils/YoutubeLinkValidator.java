package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeLinkValidator {
    private static final Pattern VALID_YOUTUBE_ADDRESS_REGEX = Pattern.compile("http(?:s?):\\/\\/(?:www\\.)?youtu(?:be\\.com\\/watch\\?v=|\\.be\\/)([\\w\\-\\_]*)(&(amp;)?[\\w\\?\u200C\u200B=]*)?");

    public static boolean validate(String source) {
        Matcher matcher = VALID_YOUTUBE_ADDRESS_REGEX.matcher(source);
        return matcher.find();
    }
}
