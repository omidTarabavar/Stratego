package Stratego;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Temp {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String a = "";
        System.out.println(a.isEmpty());
    }
    private static String fixAddress(String address){
        String result = "";
        for(int i = 0 ; i < address.length();i++){
            if(address.charAt(i) == '\\'){
                result += "\\";
            }else {
                result += address.charAt(i);
            }
        }
        return result;
    }
    private static boolean openFile(String address){
        try {
            scanner = new Scanner(Paths.get(address));
        }catch (IOException ioException){
            return false;
        }
        return true;
    }
    private static void readFile(){
        try {
            while (scanner.hasNext()) {
                System.out.printf("text: %s\n", scanner.nextLine());
            }
        }catch (NoSuchElementException noSuchElementException){
            System.out.println("File imp formed\n");
        }catch (IllegalStateException illegalStateException){
            System.out.println("Illg");
        }

    }
    private static void closeFile(){
        if(scanner != null){
            scanner.close();
        }
    }

}
