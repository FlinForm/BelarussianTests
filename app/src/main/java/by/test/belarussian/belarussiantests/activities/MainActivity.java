package by.test.belarussian.belarussiantests.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.model.Player;
import by.test.belarussian.belarussiantests.model.questions.Question;
import by.test.belarussian.belarussiantests.model.questions.Questions;
import by.test.belarussian.belarussiantests.model.Quiz;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String DEFAULT_FONTS_PATH = "fonts/Marmelad-Regular.ttf";
    static {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(DEFAULT_FONTS_PATH)
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
    private final String SHARED_PREFERENCES_KEY = "player";

    private AlertDialog startDialog, resultsDialog, rulesDialog;
    private Questions questions;

    @Override
    protected void onResume() {
        super.onResume();
        readBestPlayers();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.start_dialog);
        startDialog = builder.create();

        builder.setView(R.layout.best_results_dialog);
        resultsDialog = builder.create();

        builder.setView(R.layout.rules_dialog);
        rulesDialog = builder.create();

        questions = new Questions();
        parseJson(questions);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveBestPlayers();
        Quiz.bestPlayers.clear();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startTestButton:
                startDialog.show();
                break;
            case R.id.resultsButton:
                resultsDialog.show();
                Collections.sort(Quiz.bestPlayers);
                String[] results = Quiz.getBestResults();
                TextView name = (TextView) resultsDialog.findViewById(R.id.bestResultsName);
                assert name != null;
                name.setText(results[0]);
                TextView time = (TextView) resultsDialog.findViewById(R.id.bestResultsTime);
                assert time != null;
                time.setText(results[1]);
                TextView answers = (TextView) resultsDialog.findViewById(R.id.bestResultsAnswers);
                assert answers != null;
                answers.setText(results[2]);
                break;
            case R.id.rulesButton:
                rulesDialog.show();
                break;
            case R.id.topicButton:
                break;
            case R.id.dialogStartButton:
                Quiz.testQuestions.clear();
                Collections.shuffle(questions.getQuestions());
                Quiz.getRandomQuestions(questions.getQuestions());
                EditText personName = (EditText) startDialog.findViewById(R.id.nameEditText);
                assert personName != null;
                if ("".equals(personName.getText().toString())) {
                    Snackbar.make(v,
                            getString(R.string.name_warning),
                            BaseTransientBottomBar.LENGTH_SHORT).show();
                    return;
                }
                if (personName.getText().toString().length() > 13) {
                    Snackbar.make(v,
                            getString(R.string.name_length_warning),
                            BaseTransientBottomBar.LENGTH_SHORT).show();
                    return;
                }
                Quiz.player = new Player(personName.getText().toString(), 0L, 0);
                startActivity(new Intent(this, QuizActivity.class));
                personName.setText("");
                startDialog.hide();
                break;
            case R.id.dialogCancelButton:
                startDialog.hide();
        }
    }

    private void saveBestPlayers() {
        int listRange = 5;
        if (Quiz.bestPlayers.size() < 5) {
            listRange = Quiz.bestPlayers.size();
        }
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 0; i < listRange; i++) {
            if (Quiz.bestPlayers.get(i) != null) {
                editor.putString(SHARED_PREFERENCES_KEY + i, Quiz.bestPlayers.get(i).toString());
            }
        }
        editor.commit();
    }

    private void readBestPlayers() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
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

    private void parseJson(Questions questions) {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getResources().openRawResource(R.raw.questions);
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
