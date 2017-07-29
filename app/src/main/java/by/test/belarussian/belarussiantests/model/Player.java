package by.test.belarussian.belarussiantests.model;

import java.util.Date;

public class Player {
    private String name;
    private long time;
    private int correctAnswers;

    public Player(String name, long time, int correctAnswers) {
        this.name = name;
        this.time = time;
        this.correctAnswers = correctAnswers;
    }

    public String getName() {
        return name;
    }

    public String getFormattedTime() {
        Date date = new Date(time);
        return Quiz.formatter.format(date);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers() {
        for (int i = 0; i < Quiz.questions.size(); i++) {
            if (Quiz.questions.get(i).isAnswered()) {
                correctAnswers++;
            }
        }
        System.out.println("correct " + correctAnswers);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", correctAnswers=" + correctAnswers +
                '}';
    }
}
