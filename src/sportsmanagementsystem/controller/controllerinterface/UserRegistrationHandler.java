package sportsmanagementsystem.controller.controllerinterface;

import sportsmanagementsystem.application.constants.User;

public interface UserRegistrationHandler {
    String register(String name, String password, User userType);
}
