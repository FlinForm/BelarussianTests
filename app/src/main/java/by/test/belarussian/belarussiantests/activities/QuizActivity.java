package by.test.belarussian.belarussiantests.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.fragments.QuestionFragment;
import by.test.belarussian.belarussiantests.fragments.ResultsFragment;
import by.test.belarussian.belarussiantests.model.Quiz;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class QuizActivity extends AppCompatActivity
        implements QuestionFragment.OnTestFinishedListener {
    private final String DEFAULT_FONTS_PATH = "fonts/Marmelad-Regular.ttf";
    private static final String QUESTION_NUMBER = "fragmentNumber";
    private long startTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(DEFAULT_FONTS_PATH)
                .setFontAttrId(R.attr.fontPath)
                .build());

        startTest();
    }

    private void startTest() {
        startTime = System.currentTimeMillis();
        Fragment fragment = new QuestionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(QUESTION_NUMBER, 0);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.questionLayout, fragment).commit();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onTestFinished(long endTime) {
        Quiz.setTime(endTime - startTime);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.questionLayout, new ResultsFragment())
                .commit();
    }
}
