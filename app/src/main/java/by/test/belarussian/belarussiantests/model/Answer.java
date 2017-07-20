package by.test.belarussian.belarussiantests.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Answer {
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
