package sportsmanagementsystem.databasehandler.databasehandlerinterface.login;

import sportsmanagementsystem.application.constants.Message;

public interface UserValidator {
    Message validateUser(String userId, String password);
}
