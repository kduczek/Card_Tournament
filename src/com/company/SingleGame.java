package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SingleGame {
    private Player playerOne;
    private Player playerTwo;

    public SingleGame(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public Player playGame() {
        int[] deck = createDeck();
        int[] orderOfCardDrawing = generateCardNumbers();


        List<Integer> playerOneDeck = new ArrayList<>();
        List<Integer> playerTwoDeck = new ArrayList<>();

        for(int i = 0; i < 26; i++) {
            playerOneDeck.add(deck[orderOfCardDrawing[i]]);
        }

        for(int i = 26; i < 52; i++) {
            playerTwoDeck.add(deck[orderOfCardDrawing[i]]);
        }


        int sumOfPlayerOnePoints = sumPoints(playerOneDeck);
        int sumOfPlayerTwoPoints = sumPoints(playerTwoDeck);

        if(sumOfPlayerOnePoints == sumOfPlayerTwoPoints) {

            if(checkIfDeckHasAce(playerOneDeck) && !checkIfDeckHasAce(playerTwoDeck)) {
                return playerOne;
            } else if(!checkIfDeckHasAce(playerOneDeck) && checkIfDeckHasAce(playerTwoDeck)) {
                return playerTwo;
            } else {
                if(checkIfPlayerOneHasAceOfDirect()) {
                    return playerOne;
                } else {
                    return playerTwo;
                }
            }

        }

        if(sumOfPlayerOnePoints > sumOfPlayerTwoPoints) {
            return playerOne;
        }

        return playerTwo;
    }

    private int[] createDeck() {
        int[] deck = new int[52];
        int iterator = 0;
        for(int i = 2; i < 15; i++) {
            for(int j = 0; j < 4; j++) {
                deck[iterator] = i;
                iterator++;
            }
        }

        return deck;
    }

    private int[] generateCardNumbers() {
        Random random = new Random();
        int[] cardNumbers = new int[52];
        List<Integer> tempList = new ArrayList<>();
        int number;

        do {
            number = random.nextInt((52));
            if(!tempList.contains(number)) {
                tempList.add(number);
            }
        } while (tempList.size() != 52);

        for(int i = 0; i < 52; i++) {
            cardNumbers[i] = tempList.get(i);
        }

        return cardNumbers;
    }

    private int sumPoints(List<Integer> deck) {
        return deck
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private boolean checkIfDeckHasAce(List<Integer> deck) {
        return deck.contains(14);
    }

    private boolean checkIfPlayerOneHasAceOfDirect() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
