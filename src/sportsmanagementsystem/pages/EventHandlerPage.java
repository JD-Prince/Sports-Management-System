package sportsmanagementsystem.pages;
import sportsmanagementsystem.application.constants.Message;
import sportsmanagementsystem.application.constants.Sports;
import sportsmanagementsystem.model.Event;
import sportsmanagementsystem.model.Person;
import sportsmanagementsystem.controller.controllerinterface.OrganizerHandler;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class EventHandlerPage
{

    private final Scanner scanner = new Scanner(System.in);
    private final OrganizerHandler organizerHandler;

    EventHandlerPage(OrganizerHandler organizersEventHandler) {
        this.organizerHandler = organizersEventHandler;
    }

    void createEvent(Person person)
    {
        while (true) {
            int i = 0;
            System.out.println("Choose Your Sports\n");
            for (Sports sport : Sports.values()) {
                System.out.println(++i + " " + sport);
            }
            System.out.println(++i + " Go Back");
            int option;
            try {
                option = scanner.nextInt();
                scanner.nextLine();
                if (option == i) {
                    return;
                } else if (option > i) {
                    System.out.println("Invalid Input Try Again");
                    continue;
                }
                Sports sport = Sports.values()[option - 1];
                String title = getEventTitle();
                String location = getEventLocation();
                double amount = getEntryFee();
                String eventId = organizerHandler.createEvent(title, location, sport, amount, person.name, person.userId);
                if(eventId!=null){
                    System.out.println("Event ID : "+ eventId +"\nTitle : "+ title +"\nGame : "+sport.name+"\nEntry Fee : "+amount+"\nLocation : "+location+"\nOrganized by "+ person.name +"("+ person.userId +")");
                    System.out.println("Profile Created Successfully");
                    return;
                }
                System.out.println("Something Went Wrong");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input Format try again");
                scanner.nextLine();
            }
        }
    }


    void customizeEvent(Person person){
        while(true){
            String eventId=getEventId(person);
            if(eventId == null) return;
            Event event;
            event = organizerHandler.getEvent(person,eventId);

            if(event == null){
                System.out.println("Something went wrong");
                return;
            }
            System.out.println(event);
            System.out.println("""
                    
                    CHOOSE YOUR ACTION
                    1. CHANGE THE TITLE
                    2. CHANGE THE LOCATION
                    3. CHANGE THE ENTRY FEE
                    4. CANCEL THE EVENT
                    5. GO BACK
                    
                    """);
            int option;
            String title,location;
            double entryFee;
            try{
                option= scanner.nextInt();
                scanner.nextLine();
                switch(option){
                    case 1:
                        title=getEventTitle();
                        event.setTitle(title);
                        break;
                    case 2:
                        location=getEventLocation();
                        event.setLocation(location);
                        break;
                    case 3:
                        entryFee=getEntryFee();
                        event.setEntryFee(entryFee);
                        break;
                    case 4:
                        cancelEvent(event,person);
                        return;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid Input !");
                        continue;
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input Format Please Try Again");
                scanner.nextLine();
                continue;
            }
            Message message= organizerHandler.modifyEvent(event,person);
            if(message==Message.EVENT_UPDATED) System.out.println(event);
            else System.out.println("Something Went Wrong :(");
            break;

        }
    }

    private void cancelEvent(Event event,Person person) {
        System.out.println("Type \" YES \" to Confirm");
        String confirmation=scanner.nextLine();
        if(confirmation.equals("YES")){
            System.out.println(organizerHandler.removeEvent(event,person));
            return;
        }
        System.out.println("REQUEST DECLINED");
    }

    private String getEventId(Person person){
        while(true) {
            ArrayList<String> eventList = organizerHandler.getEventList(person);
            if(eventList.isEmpty()){
                System.out.println("You haven't created any EVENTS yet :(");
                return null;
            }
            System.out.println("\nEvent Catalogue\n");
            for (String i : eventList) {
                System.out.println(i);
            }
            System.out.println("\nEnter the Event Id to proceed or enter 0 to return\n");
            String option = scanner.nextLine();
            if (option.equals("0")) {
                return null;
            } else if (eventList.contains(option)) return option;
            System.out.println("Invalid Option Please try again");
        }
    }
    private double getEntryFee() {
        while(true){
            try {
                System.out.println("Enter the Amount");
                double amount = scanner.nextDouble();
                if (amount < 0) {
                    System.out.println("Invalid input format");
                    continue;
                }
                return amount;
            }catch(InputMismatchException e){
                System.out.println("Invalid Input Format try Again");
                scanner.nextLine();
            }
        }
    }
    private String getEventLocation() {
        while(true){
            System.out.println("Enter the Location");
            String location=scanner.nextLine();
            if(location.isBlank()){
                System.out.println("Location Should not be Empty");
                continue;
            }
            return location;
        }
    }
    private String getEventTitle(){
        while(true){
            System.out.println("Enter the Title");
            String title=scanner.nextLine();
            if(title.isBlank()){
                System.out.println("Title Should not be Empty");
                continue;
            }
            return title;
        }
    }


}
