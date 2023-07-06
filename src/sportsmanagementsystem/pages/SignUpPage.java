package sportsmanagementsystem.pages;
import sportsmanagementsystem.application.constants.User;
import sportsmanagementsystem.controller.controllerinterface.UserRegistrationHandler;

import java.util.InputMismatchException;
import java.util.Scanner;

import static sportsmanagementsystem.application.constants.User.*;
class SignUpPage {
    private final UserRegistrationHandler userUserRegistrationHandler;
    private final Scanner sc = new Scanner(System.in);
    SignUpPage(UserRegistrationHandler userUserRegistrationHandler){
        this.userUserRegistrationHandler = userUserRegistrationHandler;
    }
    public void createUser() {
        while(true) {
            System.out.println("""
                    CHOOSE YOUR TYPE
                    
                    1 ORGANIZER
                    2 PLAYER
                    """);
            int option;
            User userType;
            try{
                option=sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case 1 -> userType = ORGANIZER;
                    case 2 -> userType = PLAYER;
                    default -> {
                        System.out.println("Invalid Option");
                        return;
                    }
                }
            }catch(InputMismatchException ex){
                System.out.println("Invalid input Please Try again");
                sc.nextLine();
                continue;
            }
            String name = getName();
            String password = getPassword();
            if (password == null) return;
            String message= userUserRegistrationHandler.register(name,password,userType);
            System.out.println(message);
            if(!(message.equals("SOMETHING_WENT_WRONG"))) System.out.println("Profile Created successfully");
            return;
        }
    }
    private String getPassword() {
        while(true) {
            String password;
            System.out.println("""
                                    
                    Password must follow these condition
                    Password should at least have 6 characters
                    >Enter 0 to exit<
                    """);
            System.out.println("Create Your Password :");
            password = sc.nextLine();
            if (password.equals("0")) return null;
            if (password.length() < 6 ) {
                System.out.println("\n\t Password Doesn't match the requirement ");
                System.out.println("\n\t             Try Again");
                continue;
            }
            System.out.println("Enter Your Password Again :");
            if (password.equals(sc.nextLine())) return password;
            System.out.println(" \t\t! Password Mismatch ! Try Again !");
        }

    }
    private String getName(){
        while(true){
            System.out.println("Enter your Name");
            String name =sc.nextLine();
            if(name.isBlank()){
                System.out.println("Name Space Cannot be Empty Try Again");
                continue;
            }
            return name;
        }
    }

}
