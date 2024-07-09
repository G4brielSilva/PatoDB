package main.java.file;

import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;

public class BinaryFile {
    String filename = "people";
    RandomAccessFile file;
    int NAME_LENGTH = 64;
    int EMAIL_LENGTH = 64;
    int AGE_LENGTH = 3;
    int TOTAL_LENGTH = 131;

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

    private byte[] fixStringToWrite(String string, int length) {
        if(string.length() >= length) {
            return (string.substring(0, length)).getBytes();
        }
        return (string).getBytes();
    }

    private int getRecordCount() throws IOException {
        return (int) (this.getFileLength() / this.TOTAL_LENGTH); // Número de registros
    }

    private long getFileLength() throws IOException {
        return this.file.length();
    }

    public void writeBinaryFile(String[] data)  throws IOException{
        this.file = new RandomAccessFile(this.filename, "rw");
        long length = this.getFileLength();

        this.file.seek(length);

        byte[] parsedName = this.fixStringToWrite(data[0], NAME_LENGTH);
        byte[] parsedAge = this.fixStringToWrite(data[1], AGE_LENGTH);
        byte[] parsedEmail = this.fixStringToWrite(data[2], EMAIL_LENGTH);

        this.file.write(parsedName);
        
        length += NAME_LENGTH;
        this.file.seek(length);

        this.file.write(parsedAge);
        
        length += AGE_LENGTH;
        this.file.seek(length);

        this.file.write(parsedEmail);
        
        if (parsedEmail.length < EMAIL_LENGTH) {
            length += EMAIL_LENGTH -1;
            this.file.seek(length);
            this.file.write('\0');
        }
    }

    private String readFixedString(int length) throws IOException {
        byte[] bytes = new byte[length];
        this.file.read(bytes);
        return new String(bytes);
    }

    public String[] readBynaryFile() throws IOException {
        this.file = new RandomAccessFile(this.filename, "rw");
        long length = this.getFileLength();
        long pointer =  0;
        int count = 0;
        
        String[] records = new String[this.getRecordCount()];

        while (pointer < length) {
            this.file.seek(pointer);
    
            String name = this.readFixedString(NAME_LENGTH);
            String age = this.readFixedString(AGE_LENGTH);
            String email = this.readFixedString(EMAIL_LENGTH);

            records[count] = name + " | " + age + " | " + email;
            
            count ++;
            pointer += TOTAL_LENGTH;
        }
        return records;
    }

    
}
