package by.test.belarussian.belarussiantests.bizlogic.rvadapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.List;

import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.bizlogic.qmodel.Question;

public class AdapterUtils {
    void setAnswerImage(Question question, List<ImageView> imageViews) {
        for (int i = 0; i < imageViews.size(); i++) {
            if (question.getAnswers()[i].isCorrect()) {
                if (question.getAnswers()[i].isSelected()) {
                    imageViews.get(i).setImageResource(R.drawable.right);
                } else {
                    imageViews.get(i).setImageResource(R.drawable.wrong);
                }
            }
        }
    }

    void setDefaultAnswerImage(List<ImageView> imageViews) {
        for (ImageView imageView : imageViews) {
            imageView.setImageDrawable(null);
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

    void setCheckBoxes(Question question, List<CheckBox> checkBoxes) {
        resetCheckBoxes(checkBoxes);
        for (int i = 0; i < question.getAnswers().length; i++) {
            checkBoxes.get(i).setClickable(false);
            if (question.getAnswers()[i].isSelected()) {
                checkBoxes.get(i).setChecked(true);
            }
        }
    }

    private void resetCheckBoxes(List<CheckBox> checkBoxes) {
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setChecked(false);
        }
    }
}
