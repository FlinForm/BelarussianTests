package by.test.belarussian.belarussiantests.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import by.test.belarussian.belarussiantests.R;

public class QuestionFragment extends Fragment implements View.OnClickListener {
    private static final String QUESTION_NUMBER = "questionNumber";
    private Fragment fragment;
    private Bundle bundle;
    private OnTestFinishedListener listener;
    private int questionNumber;
    private Button firstAnswer, secondAnswer, thirdAnswer, fourthAnswer;

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

        TextView textView = (TextView) view.findViewById(R.id.question);
        textView.setText(String.valueOf(questionNumber));

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
