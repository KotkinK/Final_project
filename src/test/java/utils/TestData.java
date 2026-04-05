package utils;

import java.util.UUID;

public class TestData {

    public static String generateUniqueEmail() {
        return "test_" + UUID.randomUUID().toString().replace("-", "").substring(0, 8) + "@123.ru";
    }

    public static String generateUniqueAdName() {
        return "Ad_" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }
}