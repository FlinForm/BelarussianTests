package by.test.belarussian.belarussiantests.model.questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionsMap {
    private Map<String, List<Question>> questions = new HashMap<>();

    public void put(String key, Question value) {
        System.out.println(key);
        for (Map.Entry<String, List<Question>> entry : questions.entrySet()) {
            if (key.equals(entry.getKey())) {
                entry.getValue().add(value);
                return;
            }
        }
        List<Question> questionList = new ArrayList<>();
        questionList.add(value);
        questions.put(key, questionList);
    }

    public Map<String, List<Question>> getQuestions() {
        return questions;
    }
}
