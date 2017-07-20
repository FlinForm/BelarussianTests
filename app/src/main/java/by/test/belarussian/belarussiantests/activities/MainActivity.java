package by.test.belarussian.belarussiantests.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.model.Answer;
import by.test.belarussian.belarussiantests.model.Question;
import by.test.belarussian.belarussiantests.model.Quiz;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String DEFAULT_FONTS_PATH = "fonts/Marmelad-Regular.ttf";
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



        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(DEFAULT_FONTS_PATH)
                .setFontAttrId(R.attr.fontPath)
                .build());
        System.out.println(personName == null);
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
                parseJson();
                break;
            case R.id.topicButton:
                break;
            case R.id.dialogStartButton:
                personName = (EditText) dialog.findViewById(R.id.nameEditText);
                if ("".equals(personName.getText().toString())) {
                    Snackbar.make(v, SNACKBAR_TEXT, BaseTransientBottomBar.LENGTH_SHORT).show();
                    return;
                }
                Quiz.setName(personName.getText().toString());
                Intent intent = new Intent(this, QuizActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
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
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            baos.close();
            inputStream.close();
        } catch (IOException e) {
        }
        try {
            Quiz.questions = mapper.readValue(baos.toString(), new TypeReference<List<Question>>(){});
            /*for (Question question : Quiz.questions) {
                System.out.println(question.toString());
                for (Answer answer : question.getAnswers()) {
                    System.out.println(answer.getAnswer());
                }
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
