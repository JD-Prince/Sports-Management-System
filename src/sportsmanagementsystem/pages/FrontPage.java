package sportsmanagementsystem.pages;

import sportsmanagementsystem.application.constants.User;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.login.UserObjectProvider;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.login.UserValidator;
import sportsmanagementsystem.model.Person;
import sportsmanagementsystem.controller.controllerinterface.OrganizerHandler;
import sportsmanagementsystem.controller.controllerinterface.PlayerHandler;
import sportsmanagementsystem.controller.controllerinterface.UserRegistrationHandler;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FrontPage {
    private final Scanner scanner = new Scanner(System.in);
    private User user=null;
    private Person currentUserObj;
    private final OrganizerHandler organizerHandler;
    private final PlayerHandler playerHandler;
    private final UserObjectProvider userObjectProvider;
    private final UserRegistrationHandler userUserRegistrationHandler;
    private final UserValidator userValidator;

    public FrontPage(OrganizerHandler organizerHandler, PlayerHandler playerHandler, UserObjectProvider userObjectProvider, UserRegistrationHandler userUserRegistrationHandler, UserValidator userValidator) {
        this.organizerHandler = organizerHandler;
        this.playerHandler = playerHandler;
        this.userObjectProvider = userObjectProvider;
        this.userUserRegistrationHandler = userUserRegistrationHandler;
        this.userValidator = userValidator;
    }



    public void start(){

        while(true) {
            user=null;
            System.out.println("""
                    --------------------------Sports Management System--------------------------
                                    
                    1 LOGIN
                    2 SIGN UP
                    3 QUIT
                                    
                    """);
            int option;
            try{
                System.out.println("Enter the Option:");
                option=scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        login();
                        break;
                    case 2:
                        signUp();
                        break;
                    case 3:
                        user=null;
                        currentUserObj=null;
                        return;
                    default:
                        System.out.println("Invalid Option");
            }
            }catch(InputMismatchException e){
                System.out.println("Invalid Input Format try again");
                scanner.nextLine();
            }
        }

    }

    private void signUp() {
        new SignUpPage(userUserRegistrationHandler).createUser();
    }

    private void login() {
        String userId=new LoginPage(userValidator).login();

        if(userId!=null) {
            if(userId.contains("ORG")) user=User.ORGANIZER;
            else user=User.PLAYER;

            currentUserObj=userObjectProvider.getUserObj(userId);

            showMenu();
        }
    }
    private void showMenu() {
        if(user==User.ORGANIZER){
            new OrganizerHomePage(organizerHandler).run(currentUserObj);
            return;
        }
        new PlayerHomePage(playerHandler).run(currentUserObj);
    }
}
