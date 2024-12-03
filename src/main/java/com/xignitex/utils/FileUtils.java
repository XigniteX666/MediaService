package com.xignitex.utils;

import java.util.Random;

public class FileUtils {

    private FileUtils() {
        throw new IllegalStateException("Utility class");
    }

    //TODO: see if it's possible to create a better solution
    public static String generateOutpuFileName(String format) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString + "." + format;
    }
}
