package sportsmanagementsystem.controller.controllerinterface;

import sportsmanagementsystem.application.constants.Message;
import sportsmanagementsystem.model.Event;
import sportsmanagementsystem.model.Person;

import java.util.Hashtable;

public interface PlayerHandler {
    Hashtable<String, Event> getEvents();

    Message registerEvent(Event event,Person person);
}
