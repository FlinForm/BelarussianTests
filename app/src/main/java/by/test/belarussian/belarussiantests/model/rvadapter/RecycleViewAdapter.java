package by.test.belarussian.belarussiantests.model.rvadapter;

import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.model.question.Question;
import by.test.belarussian.belarussiantests.model.Quiz;

public class RecycleViewAdapter
        extends RecyclerView.Adapter<RecycleViewAdapter.ItemViewHolder> {
    private final List<Question> questions;

    public RecycleViewAdapter(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.setDefaultAnswerImage();
        holder.questionText.setText(questions.get(position).getQuestion());
        holder.textViews[0].setText(questions.get(position).getAnswers()[0].getAnswer());
        holder.textViews[1].setText(questions.get(position).getAnswers()[1].getAnswer());
        holder.textViews[2].setText(questions.get(position).getAnswers()[2].getAnswer());
        holder.textViews[3].setText(questions.get(position).getAnswers()[3].getAnswer());
        holder.textViews[4].setText(questions.get(position).getAnswers()[4].getAnswer());
        holder.setAnswerImage(questions.get(position));
        holder.setCardViewColor(Quiz.getTestQuestions().get(position));
        holder.strikeThroughTextViews(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder  {
        private final TextView questionText;
        private final TextView[] textViews;
        private final ImageView[] imageViews;
        private final CardView cardView;
        private final View itemView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            textViews = new TextView[5];
            imageViews = new ImageView[5];

            questionText = (TextView) itemView.findViewById(R.id.cardQuestionText);
            cardView = (CardView) itemView.findViewById(R.id.cardView);

            textViews[0] = (TextView) itemView.findViewById(R.id.cardFirstAnswerText);
            textViews[1] = (TextView) itemView.findViewById(R.id.cardSecondAnswerText);
            textViews[2] = (TextView) itemView.findViewById(R.id.cardThirdAnswerText);
            textViews[3] = (TextView) itemView.findViewById(R.id.cardFourthAnswerText);
            textViews[4] = (TextView) itemView.findViewById(R.id.cardFifthAnswerText);

            imageViews[0] = (ImageView) itemView.findViewById(R.id.cardFirstAnswerImage);
            imageViews[1] = (ImageView) itemView.findViewById(R.id.cardSecondAnswerImage);
            imageViews[2] = (ImageView) itemView.findViewById(R.id.cardThirdAnswerImage);
            imageViews[3] = (ImageView) itemView.findViewById(R.id.cardFourthAnswerImage);
            imageViews[4] = (ImageView) itemView.findViewById(R.id.cardFifthAnswerImage);
        }

        void setAnswerImage(Question question) {
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

        private void setDefaultAnswerImage() {
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[i].setImageDrawable(null);
            }
        }

        private void setCardViewColor(Question question) {
            if (question.isAnswered()) {
                cardView.setBackground(ContextCompat.getDrawable(itemView.getContext(),
                        R.drawable.border_green));
            } else {
                cardView.setBackground(ContextCompat.getDrawable(itemView.getContext(),
                        R.drawable.border_red));
            }
        }

        private void strikeThroughTextViews(Question question) {
            if (!question.getAnswers()[0].isCorrect() && question.getAnswers()[0].isSelected()) {
                textViews[0].setPaintFlags(textViews[0].getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            if (!question.getAnswers()[1].isCorrect() && question.getAnswers()[1].isSelected()) {
                textViews[1].setPaintFlags(textViews[2].getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            if (!question.getAnswers()[2].isCorrect() && question.getAnswers()[2].isSelected()) {
                textViews[2].setPaintFlags(textViews[2].getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            if (!question.getAnswers()[3].isCorrect() && question.getAnswers()[3].isSelected()) {
                textViews[3].setPaintFlags(textViews[3].getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            if (!question.getAnswers()[4].isCorrect() && question.getAnswers()[4].isSelected()) {
                textViews[4].setPaintFlags(textViews[4].getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }
    }
}
