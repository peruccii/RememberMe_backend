package com.rememberme.rememberMe.scheduledJob;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SplitDateTimeRangeInEqualIntervals {

    public static List<LocalDateTime> splitDateTimeRangesInEqualIntervals(
            List<LocalDateTime> startTimes, List<LocalDateTime> endTimes, int n) {

        List<LocalDateTime> listOfDates = new ArrayList<>();

        for (int i = 0; i < startTimes.size() - 1; i++) {
            LocalDateTime start = startTimes.get(i);
            LocalDateTime end = endTimes.get(i);

            Duration range = Duration.between(start, end);
            Duration interval = range.dividedBy(n - 1);

            LocalDateTime timeline = start;
            for (int j = 0; j < n - 1; j++) {
                listOfDates.add(timeline);
                timeline = timeline.plus(interval);
            }
            listOfDates.add(end);
        }

        return listOfDates;
    }
}
