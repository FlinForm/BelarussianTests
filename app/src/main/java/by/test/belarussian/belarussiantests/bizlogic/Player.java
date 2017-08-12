package by.test.belarussian.belarussiantests.bizlogic;

import android.support.annotation.NonNull;

import java.util.Date;

public class Player implements Comparable<Player> {
    private final String name;
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
        for (int i = 0; i < Quiz.testQuestions.size(); i++) {
            if (Quiz.testQuestions.get(i).isAnswered()) {
                correctAnswers++;
            }
        }
        System.out.println("correct: " + correctAnswers);
    }

    @Override
    public int compareTo(@NonNull Player player) {
        if (this.getCorrectAnswers() > player.getCorrectAnswers()) {
                return -1;
            } else if (this.getCorrectAnswers() < player.getCorrectAnswers()) {
            return 1;
        } else if (this.getCorrectAnswers() == player.getCorrectAnswers()) {
            if (this.getTime() > player.getTime()) {
                return 1;
            } else if (this.getTime() < player.getTime()) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return name + " " + time + " " + correctAnswers;
    }
}
