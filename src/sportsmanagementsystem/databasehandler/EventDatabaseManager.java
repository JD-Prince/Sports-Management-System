package sportsmanagementsystem.databasehandler;

import sportsmanagementsystem.application.constants.Message;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.organizers.EventDatabaseHandler;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.players.EventListProvider;
import sportsmanagementsystem.model.Event;

import java.util.Hashtable;
import java.util.Map;


public class EventDatabaseManager implements EventDatabaseHandler, EventListProvider {

    private final Hashtable<String, Event> database=new Hashtable<>();


    public EventDatabaseManager(){
    }
    public void addEvent(Event event){
        String id=event.eventId;
        database.put(id, event);
    }
    public void removeEvent(String eventId){
        database.remove(eventId);
    }

    @Override
    public Message updateEvent(Event event) {
        if(database.containsKey(event.eventId)){
            database.replace(event.eventId,event);
            return Message.EVENT_UPDATED;
        }
        return Message.INVALID;
    }

    public Event getEvent(String id){
        Event event = null;
        if (database.containsKey(id)) {
             event = database.get(id).getCopy();
        }
        return event;
    }
    public Hashtable<String,Event> getList(){
        Hashtable<String,Event> clonedHashtable = new Hashtable<>();
        for(Map.Entry<String,Event> i : database.entrySet()){
            clonedHashtable.put(i.getKey(),i.getValue().getCopy());
        }
        return clonedHashtable;
    }


}

