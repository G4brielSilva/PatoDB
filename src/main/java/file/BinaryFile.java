package main.java.file;

import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BinaryFile {
    String filename = "people.bin";
    RandomAccessFile file;
    int NAME_LENGTH = 64;
    int EMAIL_LENGTH = 64;
    int AGE_LENGTH = 3;

    public BinaryFile() {
        // this.filename = filename;
    }

    private byte[] parseStringToWrite(String string, int size) {
        byte[] bytes = new byte[size];
        byte[] sBytes = string.getBytes();

        int length = Math.min(sBytes.length, size);
        System.arraycopy(sBytes, 0, bytes, 0, length);

        for (int i = length; i < size; i++) {
            bytes[i] = ' ';
        }
        return bytes;
    }

    public void writeBinaryFile(String name, String age, String email) throws IOException{
        this.file = new RandomAccessFile(this.filename, "rw");
        this.file.seek(0);

        byte[] parsedName = this.parseStringToWrite(name, NAME_LENGTH);
        byte[] parsedAge = this.parseStringToWrite(age, AGE_LENGTH);
        byte[] parsedEmail = this.parseStringToWrite(email, EMAIL_LENGTH);

        this.file.write(parsedName);
        this.file.write(parsedAge);
        this.file.write(parsedEmail);
    }

    private String readFixedString(int length) throws IOException {
        byte[] bytes = new byte[length];
        this.file.read(bytes);
        return new String(bytes);
    }

    public String[] readBynaryFile() throws IOException {
        this.file = new RandomAccessFile(this.filename, "rw");
        this.file.seek(0);
    
        String name = this.readFixedString(NAME_LENGTH).trim();
        String age = this.readFixedString(AGE_LENGTH).trim();
        String email = this.readFixedString(EMAIL_LENGTH).trim();
        // System.out.println(name);
        return new String[]{name, age, email};
    }

    
}
