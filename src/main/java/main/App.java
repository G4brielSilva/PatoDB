package main.java.main;

import main.java.file.BinaryFile;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            System.out.println("Hi");
        
            BinaryFile binFile = new BinaryFile();
            binFile.writeBinaryFile("gabriel", "10", "gabriel@gmail.com");
            String[] data = binFile.readBynaryFile();
            for (String dt: data) {
                System.out.println(dt);
            }

            System.out.println("Bye");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
