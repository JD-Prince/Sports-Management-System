package sportsmanagementsystem.databasehandler.databasehandlerinterface.organizers;

import sportsmanagementsystem.model.Player;

import java.util.ArrayList;
import java.util.Hashtable;

public interface OrganizerDataHandler {
    void addEvent(String id, String organizerId);

    void removeEvent(String id, String organizerId);

    Hashtable<String,ArrayList<Player>> getOrganizerData(String organizerId);
}
