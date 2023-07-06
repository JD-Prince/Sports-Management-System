package sportsmanagementsystem.pages;

import sportsmanagementsystem.databasehandler.databasehandlerinterface.login.UserValidator;

import java.util.Scanner;

class LoginPage {
    Scanner scanner = new Scanner(System.in);
    private final UserValidator userValidator;

    LoginPage(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    String login(){
        String userId=getUserId();
        if(userId==null) return null;
        String password=getPassword();
        if(password==null) return null;
        switch(userValidator.validateUser(userId,password)){
            case PASSWORD_MATCHED:
                System.out.println("Login Successful");
                return userId;
            case  INCORRECT_PASSWORD:
                System.out.println("Incorrect Password ! Please Try Again");
                break;
            case USER_NOT_FOUND:
                System.out.println("User Not Found ! Check The User ID");
                break;
        }
        return null;
    }

    private String getUserId() {
        while(true){
            System.out.print("Enter your UserId or enter 0 to go back -->");
            String userId= scanner.nextLine();
            if (userId.isEmpty()){
                System.out.println("! INVALID ! userID cannot be Empty");
                continue;
            }
            if(userId.equals("0")){
                return null;
            }
            return userId;
        }
    }

    private String getPassword() {
        while(true){
            System.out.print("Enter Your Password or enter 0 to go back->");
            String password=scanner.nextLine();
            if(password.isEmpty()){
                System.out.println("! INVALID ! password cannot be empty");
                continue;
            }
            if(password.equals("0")) return null;
            return password;
        }
    }
}
