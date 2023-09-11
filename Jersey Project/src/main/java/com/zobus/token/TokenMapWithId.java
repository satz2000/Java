package com.zobus.token;
import java.util.LinkedHashMap;
import java.util.Map;

public class TokenMapWithId {
    public static Map<Long, Integer> keyWithUserID = new LinkedHashMap<>();

    public static int getUserId(Long accessKey){
        if (TokenMapWithId.keyWithUserID.get(accessKey) != null) {      // Given access key is not in the map, it returns null value, so we handled here
            return TokenMapWithId.keyWithUserID.get(accessKey);
        }else {
            return 0;       // if null value we got, then return 0
        }
    }
    public static void mappingKeyWithId(Long accesskey, int userId){
        TokenMapWithId.keyWithUserID.put(accesskey, userId);
    }
}
