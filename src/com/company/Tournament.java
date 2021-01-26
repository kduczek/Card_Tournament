package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Tournament {
    private static int NUMBER_OF_PLAYERS = 1024;
    private static int NUMBER_OF_PAIRS = 512;
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

    private void drawPairs(List<Integer> ladderOne, List<Integer> ladderTwo) {
        int number;


        while (ladderOne.size() != NUMBER_OF_PAIRS) {
            number = generateNumber(NUMBER_OF_PLAYERS);
            if (!ladderOne.contains(number)) {
                ladderOne.add(number);
            }
        }

        while (ladderTwo.size() != NUMBER_OF_PAIRS) {
            number = generateNumber(NUMBER_OF_PLAYERS);
            if (!ladderOne.contains(number) && !ladderTwo.contains(number)) {
                ladderTwo.add(number);
            }
        }
    }

    private int generateNumber(int numberOfPlayers) {
        Random random = new Random();
//        return random.nextInt((numberOfPlayers - 1) + 1) + 1;
        return random.nextInt(numberOfPlayers);
    }

    private void nextRound() {
        if(NUMBER_OF_PLAYERS > 1) {
            NUMBER_OF_PLAYERS /= 2;
            NUMBER_OF_PAIRS /= 2;
        }
    }

    private void playRound() {
        List<Integer> ladderOne = new ArrayList<>();
        List<Integer> ladderTwo = new ArrayList<>();
        Player[] stageWinners = new Player[NUMBER_OF_PAIRS];
        AtomicBoolean isFinalGame = new AtomicBoolean(false);

        drawPairs(ladderOne, ladderTwo);

        for(int i = 0; i < NUMBER_OF_PAIRS; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                Player playerOne = players[ladderOne.get(finalI)];
                Player playerTwo = players[ladderTwo.get(finalI)];

                SingleGame singleGame = new SingleGame(playerOne, playerTwo);

                if(NUMBER_OF_PAIRS == 1) isFinalGame.set(true);
                stageWinners[finalI] =  singleGame.playGame(isFinalGame.get());
            });

            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        players = new Player[NUMBER_OF_PAIRS];
        if (NUMBER_OF_PAIRS >= 0) System.arraycopy(stageWinners, 0, players, 0, NUMBER_OF_PAIRS);

        nextRound();
    }

    public void startTournament() {
        registerParticipants();

        while(NUMBER_OF_PAIRS >= 1) {
            playRound();
        }
        printWinner();
    }

    private void printWinner() {
        if(players.length == 1) {
            System.out.println(players[0].getNameAndSurname());
        }
    }

}
