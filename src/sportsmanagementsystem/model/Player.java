package sportsmanagementsystem.model;

public class Player extends Person {
    public Player(String name, String id) {
        super(name,id);
    }

    public Player getCopy(){
        return new Player(name,userId);
    }

}
