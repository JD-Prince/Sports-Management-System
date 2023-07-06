package sportsmanagementsystem.controller.controllerinterface;

import sportsmanagementsystem.application.constants.Message;
import sportsmanagementsystem.application.constants.Sports;
import sportsmanagementsystem.model.Event;
import sportsmanagementsystem.model.Person;
import sportsmanagementsystem.model.Player;

import java.util.ArrayList;

public interface OrganizerHandler{
    String createEvent(String title, String location, Sports sports, double amount, String organizationName, String organizationId);

    ArrayList<String> getEventList(Person person);

    Event getEvent(Person person, String eventId);

    Message removeEvent(Event event, Person person);

    Message modifyEvent(Event event, Person person);

    ArrayList<Player> getContestantList(Person userObj, String eventId);
}
