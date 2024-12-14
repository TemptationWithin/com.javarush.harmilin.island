package handler;

import java.util.Scanner;

public class Validator {

    public static int getValidatedSizeInput(Scanner scanner, String message){
        int size;
        while (true){
            System.out.println(message);
            if (scanner.hasNextInt()){
                size = scanner.nextInt();
                if (size > 0){
                    return size;
                } else {
                    System.out.println("Please, enter int:");
                }
            } else {
                System.out.println("Please, enter int:");
                scanner.next();
            }
        }
    }

    public static int getValidatedIntLimitInput(Scanner scanner, String message){
        int dayLimit;
        while (true){
            System.out.println(message);
            if (scanner.hasNextInt()){
                dayLimit = scanner.nextInt();
                if (dayLimit > 0){
                    return dayLimit;
                } else {
                    System.out.println("Please, enter int > 0: ");
                }
            } else {
                System.out.println("Please, enter int > 0:");
                scanner.next();
            }
        }
    }
}
