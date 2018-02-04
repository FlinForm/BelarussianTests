package by.test.belarussian.belarussiantests.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.bizlogic.Quiz;

public class QuestionFragment extends Fragment implements View.OnClickListener {
    @BindViews({
            R.id.firstAnswerCheckbox,
            R.id.secondAnswerCheckbox,
            R.id.thirdAnswerCheckbox,
            R.id.fourthAnswerCheckbox,
            R.id.fifthAnswerCheckbox}) List<CheckBox> checkBoxes;
    @BindView(R.id.questionNumber) TextView fragmentTitle;
    @BindView(R.id.question) TextView question;
    private static final String QUESTION_NUMBER = "questionNumber";
    private OnTestFinishedListener listener;
    private int questionNumber;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnTestFinishedListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            questionNumber = bundle.getInt(QUESTION_NUMBER);
        }
        if (questionNumber == 9) {
            Button button = (Button) view.findViewById(R.id.finishTestButton);
            button.setVisibility(View.VISIBLE);
            button.setClickable(true);
            button.setOnClickListener(this);
        }
        String titleText = getString(R.string.question_number) + ++questionNumber;
        fragmentTitle.setText(titleText);
        question.setText(Quiz.getTestQuestions().get(questionNumber).getQuestion());
        initCheckboxes();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.firstAnswerCheckbox:
                if (checkBoxes.get(0).isChecked()) {
                    Quiz.getTestQuestions().get(questionNumber).getAnswers()[0].setSelected(true);
                } else {
                    Quiz.getTestQuestions().get(questionNumber).getAnswers()[0].setSelected(false);
                }
                break;
            case R.id.secondAnswerCheckbox:
                if (checkBoxes.get(1).isChecked()) {
                    Quiz.getTestQuestions().get(questionNumber).getAnswers()[1].setSelected(true);
                } else {
                    Quiz.getTestQuestions().get(questionNumber).getAnswers()[1].setSelected(false);
                }
                break;
            case R.id.thirdAnswerCheckbox:
                if (checkBoxes.get(2).isChecked()) {
                    Quiz.getTestQuestions().get(questionNumber).getAnswers()[2].setSelected(true);
                } else {
                    Quiz.getTestQuestions().get(questionNumber).getAnswers()[2].setSelected(false);
                }
                break;
            case R.id.fourthAnswerCheckbox:
                if (checkBoxes.get(3).isChecked()) {
                    Quiz.getTestQuestions().get(questionNumber).getAnswers()[3].setSelected(true);
                } else {
                    Quiz.getTestQuestions().get(questionNumber).getAnswers()[3].setSelected(false);
                }
                break;
            case R.id.fifthAnswerCheckbox:
                if (checkBoxes.get(4).isChecked()) {
                    Quiz.getTestQuestions().get(questionNumber).getAnswers()[4].setSelected(true);
                } else {
                    Quiz.getTestQuestions().get(questionNumber).getAnswers()[4].setSelected(false);
                }
                break;
            case R.id.finishTestButton:
                listener.onTestFinished(System.currentTimeMillis());
                break;
        }
    }

    private void initCheckboxes() {
        for (int i = 0; i < checkBoxes.size(); i++) {
            checkBoxes.get(i).setText(
                    Quiz.getTestQuestions().get(questionNumber).getAnswers()[i].getAnswer());
            checkBoxes.get(i).setOnClickListener(this);
        }
    }

    public interface OnTestFinishedListener {
        void onTestFinished(long endTime);
    }
}
