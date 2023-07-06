package sportsmanagementsystem.application.constants;

public enum Sports {
    CHESS("CHE01","Chess"),
    CAROM("CAR02","Carom"),
    TENNIS("TEN03","Tennis"),
    LUDO("LUD04","Ludo");

    public final String id;
    public final String name;
    Sports(String id,String name){
        this.id=id;
        this.name=name;
    }


}
