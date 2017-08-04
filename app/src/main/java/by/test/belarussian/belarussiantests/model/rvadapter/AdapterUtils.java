package by.test.belarussian.belarussiantests.model.rvadapter;

import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.model.questions.Question;

public class AdapterUtils {

    void setAnswerImage(Question question, ImageView[] imageViews) {
        for (int i = 0; i < imageViews.length; i++) {
            if (question.getAnswers()[i].isCorrect()) {
                if (question.getAnswers()[i].isSelected()) {
                    imageViews[i].setImageResource(R.drawable.right);
                } else {
                    imageViews[i].setImageResource(R.drawable.wrong);
                }
            }
        }
    }

    void setDefaultAnswerImage(ImageView[] imageViews) {
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setImageDrawable(null);
        }
    }

    void setCardViewColor(Question question, CardView cardView, View itemView) {
        if (question.isAnswered()) {
            cardView.setBackground(ContextCompat.getDrawable(itemView.getContext(),
                    R.drawable.border_green));
        } else {
            cardView.setBackground(ContextCompat.getDrawable(itemView.getContext(),
                    R.drawable.border_red));
        }
    }

    void strikeThroughTextViews(Question question, TextView[] textViews) {
        for (int i = 0; i < question.getAnswers().length; i++) {
            if (!question.getAnswers()[i].isCorrect() && question.getAnswers()[i].isSelected()) {
                textViews[i].setPaintFlags(textViews[i].getPaintFlags() |
                        Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }
    }
}
