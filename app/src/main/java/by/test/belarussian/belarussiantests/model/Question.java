package by.test.belarussian.belarussiantests.model;

public class Question {
    private String question;
    private Answer[] answers;
    private String subject;

    public Question() {
    }

    public Question(String question, Answer[] answers, String topic) {
        this.question = question;
        this.answers = answers;
        this.subject = topic;
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

    private class Answer {
        private String answer;
        private boolean isCorrect;
        private boolean isSelected;

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
