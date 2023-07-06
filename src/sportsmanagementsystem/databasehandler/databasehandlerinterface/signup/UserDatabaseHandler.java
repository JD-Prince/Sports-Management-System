package sportsmanagementsystem.databasehandler.databasehandlerinterface.signup;

import sportsmanagementsystem.model.Person;

public interface UserDatabaseHandler {
    int getTotalNoOfPlayers();
    int getTotalNoOfOrganizers();
    void addUser(Person person);
}
