package by.test.belarussian.belarussiantests.bizlogic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import by.test.belarussian.belarussiantests.bizlogic.qmodel.Question;

public class Quiz {
    protected static final DateFormat formatter = new SimpleDateFormat("mm:ss");
    public static List<Player> bestPlayers = new ArrayList<>();
    public static List<Question> testQuestions = new ArrayList<>(10);
    public static Player player;

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

    public static void resetSelectedAnswers() {
        for (Question question : testQuestions) {
            question.setAnswered(false);
            for (Question.Answer answer : question.getAnswers()) {
                answer.setSelected(false);
            }
        }
    }

    public static void addBestPlayer() {
        if (player.getCorrectAnswers() == 0) {
            return;
        }
        if (bestPlayers.size() < 5) {
            bestPlayers.add(player);

        } else {
            for (int i = 0; i < 5; i++) {
                if (bestPlayers.get(i).compareTo(player) > 0) {
                    bestPlayers.add(player);
                }
            }
        }
    }

    public static String[] getBestResults() {
        String[] results = {by.test.belarussian.belarussiantests.bizlogic.utils.StringUtils.EMPTY, by.test.belarussian.belarussiantests.bizlogic.utils.StringUtils.EMPTY, by.test.belarussian.belarussiantests.bizlogic.utils.StringUtils.EMPTY};
        int arrayBounds = 5;
        if (bestPlayers.size() < 5) {
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

    public static void getTenRandomQuestions(List<Question> questionList) {
        Set<Integer> numbers = new HashSet<>();
        do {
           numbers.add((int) (Math.random() * questionList.size()));
        } while (numbers.size() < 10);

        for (Integer number : numbers) {
            testQuestions.add(questionList.get(number));
        }
    }

    public static boolean getPosition() {
        int arrayBounds = 10;
        if (bestPlayers.size() < 10) {
            arrayBounds = bestPlayers.size();
        }
        for (int i = 0; i < arrayBounds; i++) {
            if (bestPlayers.get(i).equals(player)) {
                return true;
            }
        }
        return false;
    }
}
