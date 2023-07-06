package sportsmanagementsystem.application.service;

import sportsmanagementsystem.controller.*;
import sportsmanagementsystem.controller.controllerinterface.OrganizerHandler;
import sportsmanagementsystem.controller.controllerinterface.PlayerHandler;
import sportsmanagementsystem.controller.controllerinterface.UserRegistrationHandler;
import sportsmanagementsystem.databasehandler.CredentialHandler;
import sportsmanagementsystem.databasehandler.EventDatabaseManager;
import sportsmanagementsystem.databasehandler.OrganizerDatabaseManager;
import sportsmanagementsystem.databasehandler.UserDatabaseManager;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.login.UserObjectProvider;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.login.UserValidator;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.signup.CredentialDatabaseManager;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.signup.UserDatabaseHandler;

public class Service {
    private final EventDatabaseManager eventDatabaseManager = new EventDatabaseManager();
    private final OrganizerDatabaseManager organizerDatabaseManager = new OrganizerDatabaseManager();
    public final UserObjectProvider userDatabase = new UserDatabaseManager();
    public final UserValidator credentialHandler =new CredentialHandler();
    public final UserRegistrationHandler userUserRegistrationHandler =new UserRegister((UserDatabaseHandler) userDatabase, organizerDatabaseManager, (CredentialDatabaseManager) credentialHandler);
    public final OrganizerHandler organizerController = new OrganizersController(eventDatabaseManager, organizerDatabaseManager);
    public final PlayerHandler playerHandler =new PlayerController(eventDatabaseManager, organizerDatabaseManager);

}
