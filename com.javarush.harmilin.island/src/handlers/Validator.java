package handlers;

import java.util.Scanner;

public class Validator {

    public static int getValidatedInput(Scanner scanner, String message){
        int size;
        while (true){
            System.out.println(message);
            if (scanner.hasNextInt()){
                size = scanner.nextInt();
                if (size > 19){
                    return size;
                } else {
                    System.out.println("Please, enter int > 19:");
                }
            } else {
                System.out.println("Please, enter int:");
                scanner.next();
            }
        }
    }
}
