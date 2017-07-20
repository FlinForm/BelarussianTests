package by.test.belarussian.belarussiantests.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect
public class Question {
    private String question;
    private String subject;

    private Answer[] answers;

    public Question() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", subject='" + subject + '\'' +
                ", answers=" + answers +
                '}';
    }
}
