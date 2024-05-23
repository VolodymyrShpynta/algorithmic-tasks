package com.vshpynta;

import java.util.List;
import java.util.function.Function;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toMap;

public class BakeryQualityControl {

    public static int countInvalidBoxes(List<List<String>> box_template_list) {
        if (box_template_list == null) {
            return 0;
        }
        return (int) box_template_list.stream()
                .filter(BakeryQualityControl::isInvalid)
                .count();
    }

    private static boolean isInvalid(List<String> boxTemplate) {
        var boxItemsNumbers = boxTemplate.get(0).chars()
                .boxed()
                .collect(toMap(Function.identity(), value -> 1, Integer::sum));
        var templateItemsNumbers = boxTemplate.get(1).chars()
                .boxed()
                .collect(toMap(Function.identity(), value -> 1, Integer::sum));
        if (boxItemsNumbers.size() != templateItemsNumbers.size()) {
            return true;
        }

        return boxItemsNumbers.entrySet().stream()
                .anyMatch(not(boxItemNumbers -> boxItemNumbers.getValue().equals(
                        templateItemsNumbers.getOrDefault(boxItemNumbers.getKey(), 0))));
    }

    public static void main(String[] args) {
        System.out.println(countInvalidBoxes(List.of(
                List.of("cm", "mc"),
                List.of("cm", "m"),
                List.of("cm", "mmc"),
                List.of("cm", "ccm"))));
    }
}
