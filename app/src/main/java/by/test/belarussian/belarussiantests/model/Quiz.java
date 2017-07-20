package by.test.belarussian.belarussiantests.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Quiz {
    private static String name;
    private static long time;

    public static List<Question> questions;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Quiz.name = name;
    }

    public static String getTime() {
        Date date = new Date(time);
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        return formatter.format(date);
    }

    public static void setTime(long time) {
        Quiz.time = time;
    }

    public static List<Question> getQuestions() {
        return questions;
    }

    public static void setQuestions(List<Question> questions) {
        Quiz.questions = questions;
    }
}
