package main.java.main;

import java.util.Arrays;

import main.java.file.BinaryFile;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        try {        
            BinaryFile binFile = new BinaryFile();

            String command = Arrays.copyOfRange(args, 0, 1)[0];
            String[] dataToInsert = Arrays.copyOfRange(args, 1, args.length);

            if (command.contains("w")) {
                binFile.writeBinaryFile(dataToInsert);
            }

            if (command.contains("r")) {
                String[] data = binFile.readBynaryFile();
                for (String dt: data) {
                    System.out.println(dt);
                }
            }
            

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
