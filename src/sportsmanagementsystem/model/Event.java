package sportsmanagementsystem.model;

import sportsmanagementsystem.application.constants.Sports;

public class Event{

    private String title,location;
    public final Sports game;
    private double entryFee;
    public final String organizerName, organizerId;
    public final String eventId;

    public Event(String eventId, String title, String location, Sports game, double entryFee, String organizerName, String organizerId) {
        this.eventId = eventId;
        this.title = title;
        this.location = location;
        this.game = game;
        this.entryFee = entryFee;
        this.organizerName = organizerName;
        this.organizerId = organizerId;
    }


   public  String getTitle(){return title;}

    public String getOrganizerId(){
        return organizerId;
    }

    public void setEntryFee(double entryFee) {
        this.entryFee = entryFee;
    }


    public Event getCopy(){
        return new Event(this.eventId,this.title,this.location,this.game,this.entryFee,this.organizerName,this.organizerId);
    }

    public void setTitle(String title){
        this.title=title;
    }


    public void setLocation(String location) {
        this.location = location;
    }


    public String toString(){
        return "Event ID : "+ eventId +"\nTitle : "+ title +"\nGame : "+game.name+"\nEntry Fee : "+entryFee+"\nLocation : "+location+"\nOrganized by "+ organizerName +"("+ organizerId +")";
    }

}
