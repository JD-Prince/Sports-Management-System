package sportsmanagementsystem.databasehandler;

import sportsmanagementsystem.application.constants.Message;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.signup.CredentialDatabaseManager;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.login.UserValidator;
import sportsmanagementsystem.model.LoginCredential;

import java.util.Hashtable;

import static sportsmanagementsystem.application.constants.Message.*;
public class CredentialHandler implements UserValidator, CredentialDatabaseManager {
    private final Hashtable<String,LoginCredential> credentialData = new Hashtable<>();

    public CredentialHandler(){

    }
    public Message validateUser(String userId,String password){
        if(credentialData.containsKey(userId)){
            if(credentialData.get(userId).isValidPassword(password)){

                return PASSWORD_MATCHED;
            }
            return INCORRECT_PASSWORD;
        }
        return USER_NOT_FOUND;
    }
    @Override
    public void addCredentials(LoginCredential credential) {
        credentialData.put(credential.userId,credential);

    }
}
