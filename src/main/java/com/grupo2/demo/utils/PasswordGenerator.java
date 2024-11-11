package com.grupo2.demo.utils;

import java.util.Random;

public class PasswordGenerator {
    public static String generatePassword() {
        Random random = new Random();
        int number = 1000 + random.nextInt(9000);
        return String.valueOf(number);
    }
}