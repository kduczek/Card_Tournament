package com.company;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Statistics {

    public static void averagePointsByVoivodeship(Map<Player, Integer> statistics) {
        System.out.println("Average points by voivodeship: ");

        Map<String, Double> averages =
                statistics.entrySet()
                        .stream()
                        .collect(Collectors.groupingBy(e -> e.getKey().getVoivodeship(),
                                Collectors.averagingDouble(Map.Entry::getValue)));

        DecimalFormat df = new DecimalFormat("#.00");
        for(Map.Entry<String, Double> entry : averages.entrySet()) {
            System.out.println(entry.getKey() + ": " + df.format(entry.getValue()));
        }
    }

    public static void medianOfPointsForMenAndWomen(Map<Player, Integer> statistics) {
        Map<Player, Integer> women = new HashMap<>();
        Map<Player, Integer> men = new HashMap<>();

        divideMapByGender(statistics, women, men);

        Map<Player, Integer> sortedWomen = sortMapByValues(women);
        Map<Player, Integer> sortedMen = sortMapByValues(men);

        int[] womenScoresInInt = convertMapToIntArray(sortedWomen);
        int[] menScoresInInt = convertMapToIntArray(sortedMen);


        System.out.println("\nMedian for Women: ");
        System.out.println(calculateMedian(womenScoresInInt));

        System.out.println("Median for Men: ");
        System.out.println(calculateMedian(menScoresInInt));
    }

    public static void minAndMaxScoreForStageOneWinners(Map<Player, Integer> statistics) {
        Map<Player, Integer> sortedByScore = sortMapByValues(statistics);
        int minScore = (int) sortedByScore.values().toArray()[0];
        int maxScore = (int) sortedByScore.values().toArray()[sortedByScore.size() - 1];
        System.out.println("\nLowest score: " + minScore);
        System.out.println("Highest score: " + maxScore);
    }

    public static void sumOfPointsByAgeGroups(Map<Player, Integer> statistics) {
        Map<String, Double> temp =
                statistics.entrySet()
                .stream()
                .collect(Collectors.groupingBy(e -> e.getKey().getAgeGroup(),
                        Collectors.summingDouble(Map.Entry::getValue)));

        System.out.println("\nSum of points in different age groups: ");
        for(Map.Entry<String, Double> entry : temp.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void divideMapByGender(Map<Player, Integer> statistics, Map<Player, Integer> women, Map<Player, Integer> men) {
        for(Map.Entry<Player, Integer> entry : statistics.entrySet()) {
            if(entry.getKey().getGender() == 'K') {
                women.put(entry.getKey(), entry.getValue());
            } else {
                men.put(entry.getKey(), entry.getValue());
            }
        }
    }

    private static Map<Player, Integer> sortMapByValues(Map<Player, Integer> map) {
       return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private static int[] convertMapToIntArray(Map<Player, Integer> map) {
        Integer[] scores = map.values().toArray(new Integer[0]);
        return Arrays.stream(scores).mapToInt(Integer::intValue).toArray();
    }

    private static double calculateMedian(int[] scores) {
        if (scores.length % 2 == 0)
            return ((double)scores[scores.length/2] + (double)scores[scores.length/2 - 1])/2;
        else
            return scores[scores.length/2];
    }
}
