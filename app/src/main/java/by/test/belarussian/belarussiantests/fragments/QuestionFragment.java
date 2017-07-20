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

import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.model.Quiz;

public class QuestionFragment extends Fragment implements View.OnClickListener {
    private static final String QUESTION_NUMBER = "questionNumber";
    private Fragment fragment;
    private Bundle bundle;
    private OnTestFinishedListener listener;
    private int questionNumber;
    private CheckBox firstAnswer, secondAnswer, thirdAnswer, fourthAnswer, fifthAnswer;

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
        return inflater.inflate(R.layout.question, container, false);
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

        TextView fragmentTitle = (TextView) view.findViewById(R.id.questionNumber);
        fragmentTitle.setText(getString(R.string.question_number) + (questionNumber + 1));

        TextView question = (TextView) view.findViewById(R.id.question);
        question.setText(Quiz.getQuestions().get(questionNumber).getQuestion());

        firstAnswer = (CheckBox) view.findViewById(R.id.firstAnswerCheckbox);
        firstAnswer.setText(Quiz.getQuestions().get(questionNumber).getAnswers()[0].getAnswer());

        secondAnswer = (CheckBox) view.findViewById(R.id.secondAnswerCheckbox);
        secondAnswer.setText(Quiz.getQuestions().get(questionNumber).getAnswers()[1].getAnswer());

        thirdAnswer = (CheckBox) view.findViewById(R.id.thirdAnswerCheckbox);
        thirdAnswer.setText(Quiz.getQuestions().get(questionNumber).getAnswers()[2].getAnswer());

        fourthAnswer = (CheckBox) view.findViewById(R.id.fourthAnswerCheckbox);
        fourthAnswer.setText(Quiz.getQuestions().get(questionNumber).getAnswers()[3].getAnswer());

        fifthAnswer = (CheckBox) view.findViewById(R.id.fifthAnswerCheckbox);
        fifthAnswer.setText(Quiz.getQuestions().get(questionNumber).getAnswers()[4].getAnswer());



    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println(questionNumber);
    }

    @Override
    public void onClick(View v) {
            listener.onTestFinished(System.currentTimeMillis());
            System.out.println("inside method");
    }

    public interface OnTestFinishedListener {
        void onTestFinished(long endTime);
    }
}
