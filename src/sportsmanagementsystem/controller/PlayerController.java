package sportsmanagementsystem.controller;

import sportsmanagementsystem.application.constants.Message;
import sportsmanagementsystem.controller.controllerinterface.PlayerHandler;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.players.EventListProvider;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.players.EventRegistrationManager;
import sportsmanagementsystem.model.Event;
import sportsmanagementsystem.model.Person;
import sportsmanagementsystem.model.Player;

import java.util.ArrayList;
import java.util.Hashtable;

public class PlayerController implements PlayerHandler {
    private final EventListProvider eventListProvider;
    private final EventRegistrationManager eventRegistrationManager;

    public PlayerController(EventListProvider eventListProvider, EventRegistrationManager eventRegistrationManager) {
        this.eventListProvider = eventListProvider;
        this.eventRegistrationManager = eventRegistrationManager;
    }

    @Override
    public Hashtable<String, Event> getEvents() {
            return eventListProvider.getList();
    }

    @Override
    public Message registerEvent(Event event,Person person) {
        if(isRegistered(event,person)) return Message.INVALID;
        return eventRegistrationManager.addContestant(event.getOrganizerId(),event.eventId,(Player) person);
    }

    private boolean isRegistered(Event event,Person person) {
        ArrayList<Player> playerList = eventRegistrationManager.getPlayersList(event);
        for(Player i : playerList){
            if(i.userId.equals(person.userId)) return true;
        }
        return false;
    }

}
