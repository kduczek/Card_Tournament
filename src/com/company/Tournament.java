package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Tournament {
    private static final int NUMBER_OF_PLAYERS = 1024;
    private Player[] players = new Player[NUMBER_OF_PLAYERS];

    private void registerParticipants() {
        try {
            BufferedReader reader = Files.newBufferedReader(Path.of("/Users/krystian/Desktop/PodstawyJavy/Card_Tournament/daneBZ.txt"));

            for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
                String line = reader.readLine();

                players[i] = createParticipantFromString(line);
            }


            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Player createParticipantFromString(String line) {
        String[] personalData = line.split(";");

        String name = personalData[0];
        String surname = personalData[1];
        int age = Integer.parseInt(personalData[2]);
        char gender = personalData[3].charAt(0);
        String town = personalData[4];
        String voivodeship = personalData[5];

        return new Player(name, surname, age, gender, town, voivodeship);
    }
}
