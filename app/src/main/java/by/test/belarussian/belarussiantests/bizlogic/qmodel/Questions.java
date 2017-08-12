package by.test.belarussian.belarussiantests.bizlogic.qmodel;

import java.util.ArrayList;
import java.util.List;

public class Questions {
    private List<Question> questions;
    private QuestionsMap sortedQuestions;

    public Questions() {
        questions = new ArrayList<>();
        sortedQuestions = new QuestionsMap();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        sortQuestions();
    }

    public QuestionsMap getSortedQuestions() {
        return sortedQuestions;
    }

    public void setSortedQuestions(QuestionsMap sortedQuestions) {
        this.sortedQuestions = sortedQuestions;
    }

    private void sortQuestions() {
        for (Question question : questions) {
            sortedQuestions.put(question.getSubject(), question);
        }
    }

}
