package sportsmanagementsystem.controller;

import sportsmanagementsystem.application.constants.Message;
import sportsmanagementsystem.application.constants.User;
import sportsmanagementsystem.controller.controllerinterface.UserRegistrationHandler;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.signup.NewOrganizerRegister;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.signup.UserDatabaseHandler;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.signup.CredentialDatabaseManager;
import sportsmanagementsystem.model.LoginCredential;
import sportsmanagementsystem.model.Organizer;
import sportsmanagementsystem.model.Person;
import sportsmanagementsystem.model.Player;

import static sportsmanagementsystem.application.constants.User.ORGANIZER;
import static sportsmanagementsystem.application.constants.User.PLAYER;

public class UserRegister implements UserRegistrationHandler {
    private final UserDatabaseHandler userDatabase;
    private final NewOrganizerRegister newOrganizerRegister;
    private final CredentialDatabaseManager credentialDatabaseManager;

    public UserRegister(UserDatabaseHandler userDatabase, NewOrganizerRegister newOrganizerRegister, CredentialDatabaseManager credentialDatabaseManager) {
        this.userDatabase = userDatabase;
        this.newOrganizerRegister = newOrganizerRegister;
        this.credentialDatabaseManager = credentialDatabaseManager;
    }
    public String register(String name, String password, User userType){
        String userId = null;
        if (userType == PLAYER) {
            userId = generateId(userDatabase.getTotalNoOfPlayers() + 1, PLAYER);
        } else if(userType==ORGANIZER) {
            userId = generateId(userDatabase.getTotalNoOfOrganizers() + 1, ORGANIZER);
        }
        credentialDatabaseManager.addCredentials(new LoginCredential(userId, password));
        Person person;
        if (userType == PLAYER) {
            person=new Player(name, userId);
            userDatabase.addUser(person);
            return person.toString();
        } else if(userType==ORGANIZER) {
            person=new Organizer(name, userId);
            userDatabase.addUser(person);
            newOrganizerRegister.addNewOrganizer(userId);
            return person.toString();
        }

        return Message.SOMETHING_WENT_WRONG.toString();
    }
    private String generateId(int totalNoOfUsers, User userType){
        String s = switch (userType) {
            case ORGANIZER -> "ORG01";
            case PLAYER -> "PLY02";
        };
        return s+totalNoOfUsers;
    }

}
