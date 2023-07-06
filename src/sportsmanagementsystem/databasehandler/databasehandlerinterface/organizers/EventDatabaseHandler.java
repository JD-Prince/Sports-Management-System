package sportsmanagementsystem.databasehandler.databasehandlerinterface.organizers;
import sportsmanagementsystem.application.constants.Message;
import sportsmanagementsystem.model.Event;

public interface EventDatabaseHandler {
    void addEvent(Event event);
    Event getEvent(String id);
    void removeEvent(String eventId);

    Message updateEvent(Event event);
}
