package by.test.belarussian.belarussiantests.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.bizlogic.ActivityAuxMethods;
import by.test.belarussian.belarussiantests.bizlogic.Player;
import by.test.belarussian.belarussiantests.bizlogic.qmodel.Question;
import by.test.belarussian.belarussiantests.bizlogic.qmodel.Questions;
import by.test.belarussian.belarussiantests.bizlogic.Quiz;
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

    private AlertDialog startDialog, resultsDialog;
    private Questions questions;

    @Override
    protected void onResume() {
        super.onResume();
        Quiz.bestPlayers.clear();
        ActivityAuxMethods.readBestPlayers(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildAlertDialogs();
        questions = new Questions();
        ActivityAuxMethods.parseJson(questions, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityAuxMethods.saveBestPlayers(this);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startTestButton:
                startDialog.show();
                break;
            case R.id.resultsButton:
                resultsDialog.show();
                System.out.println(Quiz.bestPlayers.size());
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
            case R.id.topicButton:
                PopupMenu popupMenu = new PopupMenu(this, v);
                for (Map.Entry entry : questions.getSortedQuestions().getQuestions().entrySet()) {
                    popupMenu.getMenu().add((String) entry.getKey());
                }
                popupMenu.setOnMenuItemClickListener(item ->  {
                    for (Map.Entry entry : questions.getSortedQuestions().getQuestions().entrySet()) {
                        if (item.getTitle().toString().equals(entry.getKey())) {
                            List<Question> questions = (List<Question>) entry.getValue();
                            Quiz.resetSelectedAnswers();
                            Quiz.testQuestions.clear();
                            Collections.shuffle(questions);
                            Quiz.getRandomQuestions(questions);
                            Quiz.player = new Player(null, 0L, 0);
                            startActivity(new Intent(this, QuizActivity.class));
                        }
                    }
                    return true;
                });
                popupMenu.show();
                break;
            case R.id.dialogStartButton:
                Quiz.resetSelectedAnswers();
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

    private void buildAlertDialogs() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.start_dialog);
        startDialog = builder.create();
        builder.setView(R.layout.best_results_dialog);
        resultsDialog = builder.create();
    }
}
