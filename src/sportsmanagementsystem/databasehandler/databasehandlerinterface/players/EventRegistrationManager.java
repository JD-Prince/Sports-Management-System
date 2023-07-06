package sportsmanagementsystem.databasehandler.databasehandlerinterface.players;

import sportsmanagementsystem.application.constants.Message;
import sportsmanagementsystem.model.Event;
import sportsmanagementsystem.model.Player;

import java.util.ArrayList;

public interface EventRegistrationManager {
    Message addContestant(String organizerId, String eventId, Player player);

    ArrayList<Player> getPlayersList(Event event);
}
