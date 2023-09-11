package com.zobus.token;

public class ValidateUser {
    public static int getUserId(String key){
        int userId = 0;
        if(isMatched(key)){
            userId = TokenMapWithId.getUserId(Long.valueOf(key));
        }
        return userId;
    }
    private static boolean isMatched (String key){
        return TokenMapWithId.keyWithUserID.containsKey(Long.valueOf(key));
    }
}
