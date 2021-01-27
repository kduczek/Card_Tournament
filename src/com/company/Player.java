package com.company;

public class Player {
    private final String name;
    private final String surname;
    private final int age;
    private final char gender;
    private final String town;
    private final String voivodeship;

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

    public String getTown() {
        return town;
    }

    public char getGender() {
        return gender;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public int getAge() {
        return age;
    }

    public String getAgeGroup() {
        int age = getAge();

        if(age < 18) {
            return "0 - 17";
        } else if(age < 30) {
            return "18 - 29";
        } else if(age < 55) {
            return "30 - 54";
        } else {
            return "55 - 99";
        }
    }
}
