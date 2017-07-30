package by.test.belarussian.belarussiantests.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonAutoDetect
public class Question {
    private String question;
    private String subject;

    @JsonIgnore
    private boolean isAnswered = false;
    private Answer[] answers;
    public Question() {}

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public String getQuestion() {
        return question;
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

    @JsonAutoDetect
    public static class Answer {
        private String answer;
        private boolean isCorrect;
        private boolean isSelected;

        public Answer() {
        }

        public Answer(String answer, boolean isCorrect, boolean isSelected) {
            this.answer = answer;
            this.isCorrect = isCorrect;
            this.isSelected = isSelected;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public boolean isCorrect() {
            return isCorrect;
        }

        public void setCorrect(boolean correct) {
            isCorrect = correct;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }
}
