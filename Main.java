import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        boolean exit = false;
        Scanner in = new Scanner(System.in);
        Menu Menu = new Menu("Test", in);
        while (!exit) {
            exit = Menu.run();
        }
        in.close();
    }
}