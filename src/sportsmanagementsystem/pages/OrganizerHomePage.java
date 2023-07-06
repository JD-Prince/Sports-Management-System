package sportsmanagementsystem.pages;
import sportsmanagementsystem.model.Person;
import sportsmanagementsystem.model.Player;
import sportsmanagementsystem.controller.controllerinterface.OrganizerHandler;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class OrganizerHomePage {
    private final OrganizerHandler organizerHandler;
    private final Scanner scanner= new Scanner(System.in);
    private Person userObj;
    OrganizerHomePage(OrganizerHandler organizerHandler) {
        this.organizerHandler = organizerHandler;
    }
    void run(Person organizer){
        while(true) {
            this.userObj=organizer;
            System.out.println("""
                    
                    1. CREATE EVENT
                    2. CUSTOMIZE EVENT
                    3. VIEW CONTESTANT
                    4. LOG OUT
                    """);
            int option;
            try{
                option= scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1 -> createEvent();
                    case 2 -> customizeEvent();
                    case 3 -> showContestant();
                    case 4 -> {
                        userObj = null;
                        return;
                    }
                    default -> System.out.println("Invalid Option Try Again");
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input format Try Again");
                scanner.nextLine();
            }
        }

    }

    private void showContestant() {
            String eventId=getEventId();
            if(eventId==null) return;
            ArrayList<Player> contestantList = organizerHandler.getContestantList(userObj,eventId);
            if(contestantList.isEmpty()){
                System.out.println("There is no participants Right now .. Please Try again later");
                return;
            }
            System.out.println("The Contestant List of the Event "+ eventId);
            for(Player i:contestantList){
                System.out.println(i);
            }
    }

    private String getEventId() {
        while(true) {
            ArrayList<String> eventList = organizerHandler.getEventList(userObj);
            if (eventList.isEmpty()) {
                System.out.println("You haven't created any events Yet");
                return null;
            }
            System.out.println("Event Catalogue");
            for (String i : eventList) {
                System.out.println(i);
            }
            System.out.println("Enter the Event ID or Enter 0 to go back");
            String option = scanner.nextLine();
            if (option.equals("0")) return null;
            if (eventList.contains(option)) {
                return option;
            }
            System.out.println("Invalid Option .. Try Again");
        }

    }

    private void customizeEvent()
    {
        new EventHandlerPage(organizerHandler).customizeEvent(userObj);
    }

    private void createEvent() {
        new EventHandlerPage(organizerHandler).createEvent(userObj);
    }
}
