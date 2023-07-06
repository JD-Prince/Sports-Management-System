package sportsmanagementsystem.databasehandler.databasehandlerinterface.signup;

import sportsmanagementsystem.model.LoginCredential;

public interface CredentialDatabaseManager {
    void addCredentials(LoginCredential credential);
}
