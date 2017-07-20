package by.test.belarussian.belarussiantests.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.fragments.QuestionFragment;
import by.test.belarussian.belarussiantests.fragments.ResultsFragment;
import by.test.belarussian.belarussiantests.model.Quiz;
import by.test.belarussian.belarussiantests.model.viewpager.MyViewPager;
import by.test.belarussian.belarussiantests.model.viewpager.ViewPagerAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class QuizActivity extends AppCompatActivity
        implements QuestionFragment.OnTestFinishedListener {
    private final String DEFAULT_FONTS_PATH = "fonts/Marmelad-Regular.ttf";
    private static final String QUESTION_NUMBER = "fragmentNumber";
    private long startTime;
    private MyViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        viewPager = (MyViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setAllowedSwipeDirection(MyViewPager.Direction.RIGHT);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(DEFAULT_FONTS_PATH)
                .setFontAttrId(R.attr.fontPath)
                .build());

        startTest();
    }

    private void startTest() {
        startTime = System.currentTimeMillis();
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
        FrameLayout layout = (FrameLayout) findViewById(R.id.questionLayout);
        layout.removeAllViews();
        Quiz.setTime(endTime - startTime);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.questionLayout, new ResultsFragment())
                .commit();
    }
}
