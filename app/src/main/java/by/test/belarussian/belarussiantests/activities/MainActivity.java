package by.test.belarussian.belarussiantests.activities;

import android.content.Context;
import android.content.Intent;
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
import java.util.List;

import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.model.Question;
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

    private final String SNACKBAR_TEXT_EMPTY = "Enter your name!";
    private final String SNACKBAR_TEXT_LENGTH = "No more then 13 symbols";

    private AlertDialog.Builder builder;
    private AlertDialog startDialog, resultsDialog;
    private EditText personName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog);
        startDialog = builder.create();

        builder.setView(R.layout.best_results);
        resultsDialog = builder.create();

        parseJson();
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
                String[] results = Quiz.getBestResults();
                TextView name = (TextView) resultsDialog.findViewById(R.id.bestResultsName);
                name.setText(results[0]);
                TextView time = (TextView) resultsDialog.findViewById(R.id.bestResultsTime);
                time.setText(results[1]);
                TextView answers = (TextView) resultsDialog.findViewById(R.id.bestResultsAnswers);
                answers.setText(results[2]);
                break;
            case R.id.rulesButton:
                Quiz.getBestResults();
                break;
            case R.id.topicButton:
                break;
            case R.id.dialogStartButton:
                personName = (EditText) startDialog.findViewById(R.id.nameEditText);
                assert personName != null;
                if ("".equals(personName.getText().toString())) {
                    Snackbar.make(v, SNACKBAR_TEXT_EMPTY, BaseTransientBottomBar.LENGTH_SHORT).show();
                    return;
                }
                if (personName.getText().toString().length() > 13) {
                    Snackbar.make(v, SNACKBAR_TEXT_LENGTH, BaseTransientBottomBar.LENGTH_SHORT).show();
                    return;
                }
                parseJson();
                Quiz.setPlayer(personName.getText().toString());
                startActivity(new Intent(this, QuizActivity.class));
                startDialog.hide();
                break;
            case R.id.dialogCancelButton:
                startDialog.hide();
        }
    }

    private void parseJson() {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getResources().openRawResource(R.raw.questions);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int length;
        try {
            while ((length = inputStream.read(buf)) != -1) {
                baos.write(buf, 0, length);
            }
            baos.close();
            inputStream.close();
            Quiz.questions = mapper.readValue(baos.toString(), new TypeReference<List<Question>>(){});
        } catch (IOException ignored) {
        }
    }
}
