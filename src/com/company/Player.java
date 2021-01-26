package com.company;

public class Player {
    private String name;
    private String surname;
    private int age;
    private char gender;
    private String town;
    private String voivodeship;

    public Player(String name, String surname, int age, char gender, String town, String voivodeship) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.town = town;
        this.voivodeship = voivodeship;
    }

    public String getNameAndSurname() {
        return name + " " + surname;
    }
}
