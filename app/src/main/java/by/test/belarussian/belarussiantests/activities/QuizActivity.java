package by.test.belarussian.belarussiantests.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.fragments.QuestionFragment;
import by.test.belarussian.belarussiantests.bizlogic.ActivityAuxMethods;
import by.test.belarussian.belarussiantests.bizlogic.Quiz;
import by.test.belarussian.belarussiantests.bizlogic.viewpager.MyViewPager;
import by.test.belarussian.belarussiantests.bizlogic.viewpager.ViewPagerAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class QuizActivity extends AppCompatActivity
        implements QuestionFragment.OnTestFinishedListener, View.OnClickListener {
    @BindView(R.id.tabDots) TabLayout tabLayout;
    @BindView(R.id.viewPager) MyViewPager viewPager;
    private static final String DEFAULT_FONTS_PATH = "fonts/Marmelad-Regular.ttf";
    static {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(DEFAULT_FONTS_PATH)
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
    private AlertDialog finishTestDialog;
    private long startTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.finish_text_dialog);
        finishTestDialog = builder.create();
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setAllowedSwipeDirection(MyViewPager.Direction.RIGHT);
        tabLayout.setupWithViewPager(viewPager, true);
        startTest();
    }

    private void startTest() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onBackPressed() {
        finishTestDialog.show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finishDialogConfirmButton:
                finishTestDialog.hide();
                break;
            case R.id.finishDialogExitButton:
                finish();
                break;
        }
    }

    @Override
    public void onTestFinished(long endTime) {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.questionLayout);
        layout.removeAllViews();
        Quiz.getPlayer().setTime(endTime - startTime);
        Quiz.checkQuestionAnswers();
        Quiz.getPlayer().setCorrectAnswers();
        if (Quiz.getPlayer().getName() != null) {
            Quiz.addBestPlayer();
            ActivityAuxMethods.saveBestPlayers(this);
        }
        startActivity(new Intent(this, ResultsActivity.class));
        finish();
    }
}
