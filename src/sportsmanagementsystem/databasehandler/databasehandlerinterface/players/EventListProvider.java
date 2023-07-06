package sportsmanagementsystem.databasehandler.databasehandlerinterface.players;

import sportsmanagementsystem.model.Event;
import java.util.Hashtable;

public interface EventListProvider {
    Hashtable<String,Event> getList();
}
