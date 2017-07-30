package by.test.belarussian.belarussiantests.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Quiz {
    protected static final DateFormat formatter = new SimpleDateFormat("mm:ss");
    public static final List<Player> bestPlayers = new ArrayList<>();
    public static List<Question> testQuestions = new ArrayList<>(10);
    public static Player player;

    public static List<Question> interQuestions;

    public static Player getPlayer() {
        return player;
    }

    public static List<Question> getTestQuestions() {
        return testQuestions;
    }

    public static void checkQuestionAnswers() {
        for (Question question : testQuestions) {
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
        int listRange = 10;
        if (bestPlayers.size() < 10) {
            listRange = bestPlayers.size();
        }
        if (player.getCorrectAnswers() == 0) {
            return;
        }
        for (int i = 0; i < listRange; i++) {
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

    public static void getRandomQuestions(List<Question> questionList) {
        Set<Integer> numbers = new HashSet<>();
        do {
           numbers.add((int) (Math.random() * questionList.size()));
        } while (numbers.size() < 10);

        for (Integer number : numbers) {
            testQuestions.add(questionList.get(number));
        }
    }
}
