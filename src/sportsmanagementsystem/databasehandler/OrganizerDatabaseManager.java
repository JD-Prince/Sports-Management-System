package sportsmanagementsystem.databasehandler;

import sportsmanagementsystem.application.constants.Message;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.players.EventRegistrationManager;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.organizers.OrganizerDataHandler;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.signup.NewOrganizerRegister;
import sportsmanagementsystem.model.Event;
import sportsmanagementsystem.model.Player;

import java.util.*;
public class OrganizerDatabaseManager implements NewOrganizerRegister, OrganizerDataHandler, EventRegistrationManager {
    public OrganizerDatabaseManager(){

    }
    private final Hashtable<String, Hashtable<String, ArrayList<Player>>> database=new Hashtable<>();

    public Message addContestant(String organizerId, String eventId, Player player){
        database.get(organizerId).get(eventId).add(player);
        return Message.REGISTRATION_SUCCESSFUL;
    }

    @Override
    public ArrayList<Player> getPlayersList(Event event) {
        return new ArrayList<>(database.get(event.getOrganizerId()).get(event.eventId));
    }

    @Override
    public void addNewOrganizer(String organizerId){
        database.put(organizerId,new Hashtable<>());
    }

    @Override
    public void addEvent(String eventId,String organizerId) {
        database.get(organizerId).put(eventId,new ArrayList<>());
    }

    @Override
    public void removeEvent(String id, String organizerId) {
        database.get(organizerId).remove(id);
    }

    @Override
    public Hashtable<String, ArrayList<Player>> getOrganizerData(String organizerId) {
        if(database.containsKey(organizerId)) {
            return new Hashtable<>(database.get(organizerId));
        } return null;
    }



}
