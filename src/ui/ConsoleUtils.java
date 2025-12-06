package ui;

import java.util.Scanner;

public class ConsoleUtils {

    private static final Scanner scanner = new Scanner(System.in);

    public static String input(String message) {
        System.out.print(message);
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("exit")) {
            return null;
        }
        return input;
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Không thể xóa màn hình!");
        }
    }
    public static void pause() {
        System.out.println("\nNhấn Enter để tiếp tục...");
        try {
            System.in.read();
        } catch (Exception ignored) {}
    }
}
