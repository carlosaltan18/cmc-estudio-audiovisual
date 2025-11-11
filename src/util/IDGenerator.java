package util;

import java.util.UUID;

public class IDGenerator {
    public static String generate(String prefix) {
        return prefix + "_" + UUID.randomUUID().toString().substring(0, 8);
    }

    public static String generateSimple() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
