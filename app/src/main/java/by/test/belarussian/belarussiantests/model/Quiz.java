package by.test.belarussian.belarussiantests.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Quiz {
    private static final DateFormat formatter = new SimpleDateFormat("mm:ss");
    public static Player[] bestPlayers = new Player[10];
    static {
        for (int i = 0; i < bestPlayers.length; i++) {
            bestPlayers[i] = null;
        }
    }
    public static List<Question> questions;
    private static Player player;


    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(String name) {
        player = new Player();
        player.setName(name);
    }

    public static List<Question> getQuestions() {
        return questions;
    }

    public static void checkQuestionAnswers() {
        for (Question question : questions) {
            for (Question.Answer answer : question.getAnswers()) {
                if (answer.isCorrect() != answer.isSelected()) {
                    question.setAnswered(false);
                    break;
                } else {
                    question.setAnswered(true);
                }
            }
        }
    }

    public static void setBestPlayer() {
        for (int i = 0; i < bestPlayers.length; i++) {
            if (bestPlayers[i] == null) {
                bestPlayers[i] = player;
                return;
            }
        }
    }

    public static String[] getBestResults() {
        String[] results = {"", "", ""};

        for (int i = 0; i < bestPlayers.length; i++) {
            if (bestPlayers[i] != null) {
                results[0] += bestPlayers[i].getName() + "\n";
                results[1] += bestPlayers[i].getTime() + "\n";
                results[2] += bestPlayers[i].getCorrectAnswers() + "\n";
            }
        }
        if (results[0].length() > 0 && results[1].length() > 0 && results[2].length() > 0) {
            results[0] = results[0].substring(0, results[0].length() - 1);
            results[1] = results[1].substring(0, results[1].length() - 1);
            results[2] = results[2].substring(0, results[2].length() - 1);
        }
        return results;
    }

    public static class Player {
        private String name;
        private long time;
        private int correctAnswers;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            Date date = new Date(time);
            return formatter.format(date);
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getCorrectAnswers() {
            return correctAnswers;
        }

        public void setCorrectAnswers() {
            for (int i = 0; i < questions.size(); i++) {
                if (questions.get(i).isAnswered()) {
                    correctAnswers++;
                }
            }
        }
    }
}
