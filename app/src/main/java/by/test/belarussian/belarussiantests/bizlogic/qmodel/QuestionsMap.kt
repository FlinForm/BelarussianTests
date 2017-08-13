package by.test.belarussian.belarussiantests.bizlogic.qmodel

class QuestionsMap {
    private var questions = mutableMapOf<String, MutableList<Question>>()

    fun put(key: String, value: Question) {
        for ((mapKey, mapValue) in questions) {
            if (key.equals(mapKey)) {
                mapValue.add(value)
            }
        }
        var questionList = mutableListOf(value)
        questions.put(key, questionList)
    }
}