package by.test.belarussian.belarussiantests.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Quiz {
    protected static final DateFormat formatter = new SimpleDateFormat("mm:ss");
    public static List<Player> bestPlayers = new ArrayList<>();
    public static List<Question> questions;
    public static Player player;


    public static Player getPlayer() {
        return player;
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
        int arrayBounds = 10;
        if (bestPlayers.size() < 10) {
            arrayBounds = bestPlayers.size();
        }
        if (player.getCorrectAnswers() == 0) {
            return;
        }

        for (int i = 0; i < arrayBounds; i++) {
            if (bestPlayers.get(i).getCorrectAnswers() < player.getCorrectAnswers()) {
                bestPlayers.add(i, player);
                return;
            }
            if (bestPlayers.get(i).getCorrectAnswers() == player.getCorrectAnswers()) {
                if (bestPlayers.get(i).getTime() > player.getTime()) {
                    bestPlayers.add(i, player);
                    return;
                }
            }
        }
        bestPlayers.add(player);
    }

    public static String[] getBestResults() {
        String[] results = {"", "", ""};
        int arrayBounds = 10;
        if (bestPlayers.size() < 10) {
            arrayBounds = bestPlayers.size();
        }

        for (int i = 0; i < arrayBounds; i++) {
                results[0] += bestPlayers.get(i).getName() + "\n";
                results[1] += bestPlayers.get(i).getFormattedTime() + "\n";
                results[2] += bestPlayers.get(i).getCorrectAnswers() + "\n";
        }

        if (results[0].length() > 0 && results[1].length() > 0 && results[2].length() > 0) {
            results[0] = results[0].substring(0, results[0].length() - 1);
            results[1] = results[1].substring(0, results[1].length() - 1);
            results[2] = results[2].substring(0, results[2].length() - 1);
        }
        return results;
    }
}
