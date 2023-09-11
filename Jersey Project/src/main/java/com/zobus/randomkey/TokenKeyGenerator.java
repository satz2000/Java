package com.zobus.randomkey;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class TokenKeyGenerator {
    static List<String> uniqueKey = new ArrayList<>();
    public static String generateToken(){
        String key = null;
        while (key == null) {
            String generatedKey = RandomStringUtils.randomNumeric(15);
            if (!uniqueKey.contains(generatedKey)) {
                key = generatedKey;
                uniqueKey.add(key);
            }
        }
        return key;
    }
}
