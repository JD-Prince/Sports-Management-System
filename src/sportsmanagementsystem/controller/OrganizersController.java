package sportsmanagementsystem.controller;

import sportsmanagementsystem.application.constants.Message;
import sportsmanagementsystem.application.constants.Sports;
import sportsmanagementsystem.controller.controllerinterface.OrganizerHandler;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.organizers.OrganizerDataHandler;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.organizers.EventDatabaseHandler;
import sportsmanagementsystem.model.Event;
import sportsmanagementsystem.model.Person;
import sportsmanagementsystem.model.Player;

import java.util.ArrayList;
import java.util.Hashtable;

public class OrganizersController implements OrganizerHandler {
    private final EventDatabaseHandler eventDatabaseHandler;
    private final OrganizerDataHandler organizerDataHandler;

    private final Hashtable<Sports,Integer> eventCount =new Hashtable<>();


    public OrganizersController(EventDatabaseHandler eventDatabaseHandler, OrganizerDataHandler organizerDataHandler) {
        this.eventDatabaseHandler = eventDatabaseHandler;
        this.organizerDataHandler = organizerDataHandler;
    }
    public String createEvent(String title, String location, Sports sports, double amount, String organizerName, String organizerId) {

        String eventId;
        if(!(eventCount.containsKey(sports))){
            eventCount.put(sports,0);
        }
        eventCount.replace(sports, eventCount.get(sports)+1);
        eventId=sports.id+(eventCount.get(sports));
        Event event=new Event(eventId,title, location, sports, amount, organizerName, organizerId);
        eventDatabaseHandler.addEvent(event);
        organizerDataHandler.addEvent(eventId,organizerId);
        return event.eventId;
    }

    @Override
    public ArrayList<String> getEventList(Person person) {
        return new ArrayList<>(organizerDataHandler.getOrganizerData(person.userId).keySet());
    }

    @Override
    public Event getEvent(Person person, String eventId){
        Event event = eventDatabaseHandler.getEvent(eventId);
        if(event == null) return null;
        if(!(event.getOrganizerId().equals(person.userId))){
            return null;
        }
        return event;
    }

    @Override
    public Message removeEvent(Event event, Person person) {
        if(event.getOrganizerId().equals(person.userId)){

            eventDatabaseHandler.removeEvent(event.eventId);
            organizerDataHandler.removeEvent(event.eventId, person.userId);

            return Message.EVENT_REMOVED;
        }
        return Message.INVALID_USER;
    }

    @Override
    public Message modifyEvent(Event event, Person person) {
        if(event.getOrganizerId().equals(person.userId)) {
            return eventDatabaseHandler.updateEvent(event);
        }
        return Message.INVALID_USER;
    }

    @Override
    public ArrayList<Player> getContestantList(Person organizer, String eventId) {
        return organizerDataHandler.getOrganizerData(organizer.userId).get(eventId);
    }
}
