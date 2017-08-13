package by.test.belarussian.belarussiantests.bizlogic.qmodel

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonIgnore

@JsonAutoDetect
class Question {
    var question: String = ""
    var subject: String = ""

    @JsonIgnore
    var isAnswered: Boolean = false
    var answers = emptyArray<Answer>()

    @JsonAutoDetect
    class Answer {
        var answer: String = ""
        var isCorrect: Boolean = false
        var isSelected: Boolean = false
    }
}