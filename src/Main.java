import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Example - C:/Users/ADMIN/Desktop/test");
        System.out.println("Enter your folder path to organize: " );
        File file = new File(scanner.nextLine());
        if (file.exists()) {
            Organizer organizer = new Organizer(file.getPath());
            organizer.organizeFiles();
        } else {
            System.err.println("Path not found");
        }
    }
}