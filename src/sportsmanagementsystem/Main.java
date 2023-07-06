package sportsmanagementsystem;


import sportsmanagementsystem.application.Application;
import sportsmanagementsystem.model.Person;
import sportsmanagementsystem.model.Player;

public class Main {
    public static void main(String[] args) {
        Person a = new Player("wfed","WEd");
        Application application = Application.getInstance();
        application.start();
    }
}
