package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tournament implements Runnable {
    private static int NUMBER_OF_PLAYERS = 1024;
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

    private void drawPairs() {
        int numberOfPairs = NUMBER_OF_PLAYERS / 2;
        List<Integer> ladderOne = new ArrayList<>();
        List<Integer> ladderTwo = new ArrayList<>();
        int number;


        do {
            number = generateNumber(numberOfPairs * 2);
            if (!ladderOne.contains(number)) {
                ladderOne.add(number);
            }
        } while (ladderOne.size() != numberOfPairs);

        do {
            number = generateNumber(numberOfPairs * 2);
            if (!ladderOne.contains(number) && !ladderTwo.contains(number)) {
                ladderTwo.add(number);
            }
        } while (ladderTwo.size() != numberOfPairs);

        for(int i = 0; i < numberOfPairs; i++) {
            System.out.println(ladderOne.get(i) + " " + ladderTwo.get(i));
        }

    }

    private int generateNumber(int numberOfPlayers) {
        Random random = new Random();
        return random.nextInt((numberOfPlayers - 1) + 1) + 1;
    }


    @Override
    public void run() {
        //TODO
        drawPairs();
    }
}
