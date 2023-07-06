package sportsmanagementsystem.databasehandler.databasehandlerinterface.login;

import sportsmanagementsystem.model.Person;

public interface UserObjectProvider {
    Person getUserObj(String id);
}
