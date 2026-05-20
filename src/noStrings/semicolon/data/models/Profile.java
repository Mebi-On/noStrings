package noStrings.semicolon.data.models;

import java.time.LocalDate;

public class Profile {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String currentLocation;
    private BodyType bodyType;
    private Prefrence prefrence;
    private int heightInCM;
    private Complexion complexion;


    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setBodyType(BodyType bodyType){this.bodyType = bodyType;}

    public BodyType getBodyType(){return bodyType;}

    public void setPrefrence(Prefrence prefrence){this.prefrence = prefrence;}

    public Prefrence getPrefrence () {return prefrence;}

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getHeightInCM() {
        return heightInCM;
    }

    public void setHeightInCM(int heightInCM) {
        this.heightInCM = heightInCM;
    }

    public Complexion getComplexion() {
        return complexion;
    }

    public void setComplexion(Complexion complexion) {
        this.complexion = complexion;
    }
}
