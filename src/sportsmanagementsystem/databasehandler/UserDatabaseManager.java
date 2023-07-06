package sportsmanagementsystem.databasehandler;

import sportsmanagementsystem.databasehandler.databasehandlerinterface.signup.UserDatabaseHandler;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.login.UserObjectProvider;
import sportsmanagementsystem.model.Organizer;
import sportsmanagementsystem.model.Person;
import sportsmanagementsystem.model.Player;
import java.util.Hashtable;

public class UserDatabaseManager implements UserDatabaseHandler, UserObjectProvider {
    public UserDatabaseManager() {
    }

    private final Hashtable<String,Person> userObjects= new Hashtable<>();
    private int totalNoOfPlayers;
    private int totalNoOfOrganizers;
    public void addUser(Person person){
            userObjects.put(person.userId,person);
            if(person instanceof Organizer) totalNoOfOrganizers++;
            else {
                totalNoOfPlayers++;
            }

    }

    public Person getUserObj(String id){
        if(userObjects.get(id) instanceof Organizer){
            return ((Organizer) userObjects.get(id)).getCopy();

        }
        return ((Player) userObjects.get(id)).getCopy();

    }

    public int getTotalNoOfPlayers() {
        return totalNoOfPlayers;
    }
    public int getTotalNoOfOrganizers() {
        return totalNoOfOrganizers;
    }
}
