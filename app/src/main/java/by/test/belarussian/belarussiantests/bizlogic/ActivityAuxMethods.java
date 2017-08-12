package by.test.belarussian.belarussiantests.bizlogic;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.bizlogic.qmodel.Question;
import by.test.belarussian.belarussiantests.bizlogic.qmodel.Questions;

public class ActivityAuxMethods {
    private static final String SHARED_PREFERENCES_KEY = "player";

    public static void saveBestPlayers(Context context) {
        int listRange = 5;
        if (Quiz.bestPlayers.size() < 5) {
            listRange = Quiz.bestPlayers.size();
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 0; i < listRange; i++) {
            if (Quiz.bestPlayers.get(i) != null) {
                editor.putString(SHARED_PREFERENCES_KEY + i, Quiz.bestPlayers.get(i).toString());
            }
        }
        editor.commit();
    }

    public static void readBestPlayers(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Map<String, ?> savedData = sharedPreferences.getAll();

        for (Map.Entry<String, ?> savedPlayer : savedData.entrySet()) {
            if (savedPlayer.getKey().startsWith(SHARED_PREFERENCES_KEY)) {
                String[] player = savedPlayer.getValue().toString().split(" ");
                Quiz.bestPlayers.add(new Player(player[0],
                        Long.parseLong(player[1]),
                        Integer.parseInt(player[2])));
            }
        }
    }

    public static void parseJson(Questions questions, Context context) {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = context.getResources().openRawResource(R.raw.questions);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte buffer[] = new byte[1024];
        int length;
        try {
            while ((length = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            baos.close();
            inputStream.close();
            questions.setQuestions(mapper.readValue(baos.toString(),
                    new TypeReference<List<Question>>(){}));
        } catch (IOException ignored) {
        }
    }
}
