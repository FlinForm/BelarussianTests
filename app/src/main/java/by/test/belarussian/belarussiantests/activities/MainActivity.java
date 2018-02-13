package by.test.belarussian.belarussiantests.activities;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.bizlogic.utils.ActivityUtils;
import by.test.belarussian.belarussiantests.bizlogic.Player;
import by.test.belarussian.belarussiantests.bizlogic.qmodel.Question;
import by.test.belarussian.belarussiantests.bizlogic.qmodel.Questions;
import by.test.belarussian.belarussiantests.bizlogic.Quiz;
import by.test.belarussian.belarussiantests.fragments.TopicsDialogFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static by.test.belarussian.belarussiantests.bizlogic.utils.StringUtils.EMPTY;
import static by.test.belarussian.belarussiantests.bizlogic.utils.StringUtils.isNullOrEmpty;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener, TopicsDialogFragment.OnTestStartListener {
    private static final String DEFAULT_FONTS_PATH = "fonts/Marmelad-Regular.ttf";

    static {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(DEFAULT_FONTS_PATH)
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    private AlertDialog startDialog, resultsDialog, rulesDialog;
    private Fragment topicsFragment;

    @Override
    protected void onResume() {
        super.onResume();
        Quiz.bestPlayers.clear();
        ActivityUtils.readBestPlayers(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityUtils.parseJson(Questions.getInstance(), this);
        buildDialogs();
    }


    @Override
    protected void onPause() {
        super.onPause();
        ActivityUtils.saveBestPlayers(this);
    }

    @Override
    public void onBackPressed() {
        if (topicsFragment != null) {
            getFragmentManager().beginTransaction().detach(topicsFragment).commit();
            topicsFragment = null;
        } else {
            finish();
        }
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
                topicsFragment = new TopicsDialogFragment();
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragmentContainer, topicsFragment)
                        .commit();
                break;
            case R.id.dialogStartButton:
                startTest(EMPTY, v);
                break;
            case R.id.dialogCancelButton:
                startDialog.hide();
                break;
            case R.id.developersButton:
                rulesDialog.show();
                break;
        }
    }

    @Override
    public void onTestStart(@NonNull String topic) {
        startTest(topic, null);
        topicsFragment = null;
    }

    private void startTest(String topic, View view) {
        Quiz.resetSelectedAnswers();
        Quiz.testQuestions.clear();
        List<Question> questionList = isNullOrEmpty(topic) ?
                Questions.getInstance().getQuestions() :
                Questions.getInstance().getSortedQuestions().getQuestions().get(topic);
        Collections.shuffle(questionList);
        Quiz.getTenRandomQuestions(questionList);
        if (!isNullOrEmpty(topic)) {
            Quiz.player = new Player(EMPTY, 0L, 0);
        } else if (view != null) {
            EditText personName = (EditText) startDialog.findViewById(R.id.nameEditText);
            if (isNullOrEmpty(personName.getText().toString())) {
                Snackbar.make(view,
                        getString(R.string.name_warning),
                        BaseTransientBottomBar.LENGTH_SHORT).show();
                return;
            }
            if (personName.getText().toString().length() > 13) {
                Snackbar.make(view,
                        getString(R.string.name_length_warning),
                        BaseTransientBottomBar.LENGTH_SHORT).show();
                return;
            }
            Quiz.player = new Player(personName.getText().toString(), 0L, 0);
            personName.setText(EMPTY);
        }
        startDialog.hide();
        startActivity(new Intent(this, QuizActivity.class));
    }

    private void buildDialogs() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.start_dialog);
        startDialog = builder.create();

        builder.setView(R.layout.best_results_dialog);
        resultsDialog = builder.create();

        builder.setView(R.layout.developers_dialog);
        rulesDialog = builder.create();
    }
}