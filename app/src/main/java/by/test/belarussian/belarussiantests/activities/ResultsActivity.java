package by.test.belarussian.belarussiantests.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.model.Quiz;
import by.test.belarussian.belarussiantests.model.RecycleViewAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ResultsActivity extends AppCompatActivity {
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

        RecycleViewAdapter adapter = new RecycleViewAdapter(Quiz.getTestQuestions());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        TextView correctResults = (TextView) findViewById(R.id.correctResultsTextView);
        correctResults.setText(getString(R.string.results_text)
                + " "
                + Quiz.getPlayer().getCorrectAnswers()
                + "/"
                + Quiz.getTestQuestions().size());

        TextView time = (TextView) findViewById(R.id.resultsTimeTextView);
        time.setText(Quiz.getPlayer().getFormattedTime());

        View button = findViewById(R.id.resultsReturnButton);
        button.setOnClickListener(event -> finish());

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
        super.onBackPressed();
        finish();
    }
}
