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

    private final String SNACKBAR_TEXT = "Enter your name!";
    private final int REQUEST_CODE = 13;

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private EditText personName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog);
        dialog = builder.create();

        parseJson();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startTestButton:
                dialog.show();
                break;
            case R.id.resultsButton:
                break;
            case R.id.rulesButton:
                break;
            case R.id.topicButton:
                break;
            case R.id.dialogStartButton:
                personName = (EditText) dialog.findViewById(R.id.nameEditText);
                assert personName != null;
                if ("".equals(personName.getText().toString())) {
                    Snackbar.make(v, SNACKBAR_TEXT, BaseTransientBottomBar.LENGTH_SHORT).show();
                    return;
                }
                Quiz.setName(personName.getText().toString());
                Intent intent = new Intent(this, QuizActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                dialog.hide();
                break;
            case R.id.dialogCancelButton:
                dialog.hide();
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
        } catch (IOException ignored) {
        }
        try {
            Quiz.questions = mapper.readValue(baos.toString(), new TypeReference<List<Question>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
