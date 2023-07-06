package sportsmanagementsystem.model;

public abstract class Person{
    public final String name;
    public final String userId;

    public Person(String name,String userId){
        this.name = name;
        this.userId=userId;
    }

    public String toString(){
        return "User ID : "+this.userId+"\tName : "+this.name;
    }

}
