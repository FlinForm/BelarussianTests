package by.test.belarussian.belarussiantests.model;

import java.util.ArrayList;
import java.util.List;

public class BestResultsClass {
    private List<Result> bestResults;

    public BestResultsClass() {
        bestResults = new ArrayList<>(10);
    }

    private class Result {
        private String name;
        private int correctAnswers;
        private long time;
    }
}
