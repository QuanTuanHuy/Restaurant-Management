package hust.project.restaurant_management.utils;

public class GenerateCodeUtils {
    public static String generateCode(String prefix, Long id) {
        return prefix + String.format("%06d", id);
    }
}
