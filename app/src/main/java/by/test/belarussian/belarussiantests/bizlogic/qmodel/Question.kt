package by.test.belarussian.belarussiantests.bizlogic.qmodel

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonIgnore

@JsonAutoDetect
class Question {
    private var question = String
    private var subject = String

    @JsonIgnore
    private var isAnswered = false
    private var answers = emptyArray<Answer>()

    @JsonAutoDetect
    class Answer {
        private var answer = String
        private var isCorrect: Boolean = false
        private var isSelected: Boolean = false

    }
}