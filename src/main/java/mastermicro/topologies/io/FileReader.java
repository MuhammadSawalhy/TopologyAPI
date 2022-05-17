package mastermicro.topologies.io;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FileReader {
    public static String readFile(String filepath) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();
        try (Scanner input = new Scanner(new File(filepath))) {
            while (input.hasNextLine())
                content.append(input.nextLine()).append("\n");
        }
        return content.toString();
    }
}
