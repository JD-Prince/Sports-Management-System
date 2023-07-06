package sportsmanagementsystem.pages;

import sportsmanagementsystem.application.constants.Message;
import sportsmanagementsystem.model.Event;
import sportsmanagementsystem.model.Person;
import sportsmanagementsystem.controller.controllerinterface.PlayerHandler;

import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

class PlayerHomePage {
    private Person person;
    private final PlayerHandler playerHandler;
    Scanner scanner = new Scanner(System.in);

    PlayerHomePage(PlayerHandler playerHandler) {
        this.playerHandler = playerHandler;
    }

    void run(Person person){
        while(true){
            this.person=person;
            System.out.println("""
                    
                    CHOOSE YOUR ACTION
                    
                    1. GO TO EVENT REGISTRATION
                    2. LOG OUT
                    
                    """);
            int option;
            try{
                option=scanner.nextInt();
                scanner.nextLine();
                switch (option){
                    case 1:
                        eventRegistration();
                        break;
                    case 2:
                        this.person =null;
                        return;
                    default:
                        System.out.println("Invalid Input TryAgain");
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid Input Format Please Try Again");
                scanner.nextLine();
            }
        }
    }

    private void eventRegistration() {
            Event event = getEvent();
            if(event==null) return;
            System.out.println(event);
            System.out.println("Type \" YES \" to confirm Registration");
            String confirmation = scanner.nextLine();
            if(confirmation.equals("YES")){
                Message message= playerHandler.registerEvent(event,person);
                if(message==Message.INVALID){
                    System.out.println("User Already Registered");
                    return;
                }
                System.out.println("Registration Successful");
                return;
            }
            System.out.println("Request Declined");
    }
    private Event getEvent(){
        while(true) {
            Hashtable<String, Event> eventsData = playerHandler.getEvents();
            if(eventsData==null || eventsData.isEmpty()){
                System.out.println("There is no events Right now . Please Try Again Later");
                return null;
            }
            for (Map.Entry<String, Event> i : eventsData.entrySet()) {
                System.out.println(i.getKey() + "-->" + i.getValue().getTitle());
            }
            System.out.println("Enter Event Id to Register or type 0 to go back");
            String option = scanner.nextLine();
            if (option.equals("0")) return null;
            else if (eventsData.containsKey(option)) {
                return eventsData.get(option);
            }
            System.out.println("Invalid input Try Again");
        }

    }
}
