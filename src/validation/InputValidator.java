package validation;

public class InputValidator {

    public static boolean isValidName(String Name) {
        return Name != null && Name.trim().length() > 0;
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        return phone.matches("\\d{9,11}");
    }

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

}
