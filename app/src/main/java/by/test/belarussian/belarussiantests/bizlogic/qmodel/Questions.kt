package by.test.belarussian.belarussiantests.bizlogic.qmodel

class Questions {
    var questions = mutableListOf<Question>()
    set(value) {
        questions = value
        sortQuestions()
    }

    var sortedQuestions = QuestionsMap()

    private fun sortQuestions() {
        for (question in questions) {
            sortedQuestions.put(question.subject, question)
        }
    }
}