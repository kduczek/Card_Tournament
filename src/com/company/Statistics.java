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

        for(Map.Entry<Player, Integer> entry : statistics.entrySet()) {
            if(entry.getKey().getGender() == 'K') {
                women.put(entry.getKey(), entry.getValue());
            } else {
                men.put(entry.getKey(), entry.getValue());
            }
        }

        Map<Player, Integer> sortedWomen =
                women.entrySet()
                        .stream()
                        .sorted(Map.Entry.<Player, Integer>comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Map<Player, Integer> sortedMen =
                men.entrySet()
                        .stream()
                        .sorted(Map.Entry.<Player, Integer>comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Integer[] womenScores = sortedWomen.values().toArray(new Integer[0]);
        int[] womenScoresInInt = Arrays.stream(womenScores).mapToInt(Integer::intValue).toArray();

        Integer[] menScores = sortedWomen.values().toArray(new Integer[0]);
        int[] menScoresInInt = Arrays.stream(menScores).mapToInt(Integer::intValue).toArray();

        double womenMedian;
        double menMedian;

        System.out.println("Median for Women: ");
        if (womenScores.length % 2 == 0)
            womenMedian = ((double)womenScores[womenScores.length/2] + (double)womenScores[womenScores.length/2 - 1])/2;
        else
            womenMedian = (double) womenScores[womenScores.length/2];

        System.out.println(womenMedian);

        System.out.println("Median for Men: ");
        if (menScores.length % 2 == 0)
            menMedian = ((double)menScores[menScores.length/2] + (double)menScores[menScores.length/2 - 1])/2;
        else
            menMedian = (double) menScores[menScores.length/2];

        System.out.println(menMedian);
    }
}
