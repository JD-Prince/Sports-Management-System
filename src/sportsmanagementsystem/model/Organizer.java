package sportsmanagementsystem.model;



public class Organizer extends Person {

    public Organizer(String name, String id) {
        super(name,id);
    }
    public Organizer getCopy(){
        return new Organizer(name,userId);
    }
}

