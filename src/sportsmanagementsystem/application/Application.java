package sportsmanagementsystem.application;
import sportsmanagementsystem.application.service.Service;
import sportsmanagementsystem.controller.controllerinterface.OrganizerHandler;
import sportsmanagementsystem.controller.controllerinterface.PlayerHandler;
import sportsmanagementsystem.controller.controllerinterface.UserRegistrationHandler;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.login.UserObjectProvider;
import sportsmanagementsystem.databasehandler.databasehandlerinterface.login.UserValidator;
import sportsmanagementsystem.pages.FrontPage;
public class Application{
    private static Application application=null;
    private final UserRegistrationHandler userRegistrationHandler;
    private final UserValidator userValidator;
    private final OrganizerHandler organizerHandler;
    private final PlayerHandler playerHandler;
    private final UserObjectProvider userObjectProvider;
    private final Service service = new Service();
    private Application(){
        this.userRegistrationHandler = service.userUserRegistrationHandler;
        this.userValidator = service.credentialHandler;
        this.organizerHandler = service.organizerController;
        this.playerHandler = service.playerHandler;
        this.userObjectProvider=service.userDatabase;
    }
    public static Application getInstance(){
        if(application==null){
            application=new Application();
        }
        return application;
    }
    public void start(){
        new FrontPage(organizerHandler, playerHandler, userObjectProvider, userRegistrationHandler, userValidator).start();
    }
}
