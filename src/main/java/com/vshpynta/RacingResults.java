package com.vshpynta;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacingResults {

    private static final Map<Integer, Integer> points = Map.of(
            1, 10,
            2, 6,
            3, 4,
            4, 3,
            5, 2,
            6, 1
    );

    public static void printClassification(List<List<Integer>> rawData) {
        if (rawData == null) {
            return;
        }
        var scoresByRacers = rawData.stream()
                .collect(Collectors.toMap(l -> l.get(1), //group by racer
                        l -> points.getOrDefault(l.get(2), 0), //calculate score
                        Integer::sum)); //sum scores from different races

        scoresByRacers.entrySet().stream() // (racerId, totalScore)
                .max(Comparator.comparing((Map.Entry<Integer, Integer> e) -> e.getValue()) //compare by score firstly
                        .thenComparing(Comparator.comparing((Map.Entry<Integer, Integer> e) -> e.getKey())
                                .reversed())) //then compare by racerId in reverse order (lower number has higher priority)
                .ifPresentOrElse(winner -> System.out.println(winner.getKey() + " " + winner.getValue()),
                        System.out::println);

        var losers = scoresByRacers.entrySet().stream()
                .filter(entry -> entry.getValue() == 0)
                .map(Map.Entry::getKey)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(losers);
    }

    public static void main(String[] args) {
//        printClassification(List.of(
//                List.of(2001, 1001, 3),
//                List.of(2001, 1002, 2),
//                List.of(2002, 1003, 1),
//                List.of(2002, 1001, 2),
//                List.of(2002, 1002, 3),
//                List.of(2001, 1003, 1)));
        printClassification(List.of(
                List.of(2001, 1001, 3),
                List.of(2001, 1002, 2),
                List.of(2002, 1003, 1),
                List.of(2002, 1001, 2),
                List.of(2002, 1002, 3),
                List.of(2001, 1003, 1),
                List.of(2001, 1004, 8),
                List.of(2001, 1005, 9)));
//        printClassification(List.of(
//                List.of(2001, 1001, 1),
//                List.of(2001, 1002, 2),
//                List.of(2002, 1003, 1),
//                List.of(2002, 1001, 1),
//                List.of(2002, 1002, 3),
//                List.of(2001, 1003, 1),
//                List.of(2001, 1004, 8),
//                List.of(2001, 1005, 9)));
    }
}
