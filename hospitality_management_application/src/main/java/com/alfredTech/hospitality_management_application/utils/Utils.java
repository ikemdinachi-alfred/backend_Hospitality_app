package com.alfredTech.hospitality_management_application.utils;

import java.security.SecureRandom;

public class Utils {
    private static  final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
    private static final SecureRandom secureRandom = new SecureRandom();


    private static  String generateRandomConfirmationCode(int length) {
        StringBuilder randomCode = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomInt = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
            char randomChar = ALPHANUMERIC_STRING.charAt(randomInt);
            randomCode.append(randomChar);
        }
        return randomCode.toString();
    }
}
