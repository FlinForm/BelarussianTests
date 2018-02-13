package by.test.belarussian.belarussiantests.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.bizlogic.Quiz;
import by.test.belarussian.belarussiantests.bizlogic.rvadapter.RecycleViewAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static by.test.belarussian.belarussiantests.bizlogic.utils.StringUtils.*;


public class ResultsActivity extends AppCompatActivity {
    @BindView(R.id.resultsReturnButton) View button;
    @BindView(R.id.correctResultsTextView) TextView correctResults;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.resultsTimeTextView) TextView time;
    private static final String DEFAULT_FONTS_PATH = "fonts/Marmelad-Regular.ttf";
    static {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(DEFAULT_FONTS_PATH)
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);
        RecycleViewAdapter adapter = new RecycleViewAdapter(Quiz.getTestQuestions());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Log.d("TTTAG", Integer.toString(Quiz.getPlayer().getCorrectAnswers()));
        String results = getString(R.string.results_text)
                + SPACE
                + Quiz.getPlayer().getCorrectAnswers()
                + "/"
                + Quiz.getTestQuestions().size();
        correctResults.setText(results);
        time.setText(Quiz.getPlayer().getFormattedTime());
        button.setOnClickListener(event -> finish());
        Log.d("TTTAG", results);
        if (Quiz.bestPlayers.contains(Quiz.getPlayer()) && Quiz.getPosition()) {
            Snackbar.make(getWindow().getDecorView().getRootView(),
                    R.string.perform_best_result,
                    Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}