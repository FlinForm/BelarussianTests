package by.test.belarussian.belarussiantests.model;

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
        holder.firstAnswer.setText(questions.get(position).getAnswers()[0].getAnswer());
        holder.secondAnswer.setText(questions.get(position).getAnswers()[1].getAnswer());
        holder.thirdAnswer.setText(questions.get(position).getAnswers()[2].getAnswer());
        holder.fourthAnswer.setText(questions.get(position).getAnswers()[3].getAnswer());
        holder.fifthAnswer.setText(questions.get(position).getAnswers()[4].getAnswer());
        holder.setAnswerImage(questions.get(position));
        holder.setCardViewColor(Quiz.getQuestions().get(position));
        System.out.println(questions.get(position).isAnswered());
        System.out.println(Quiz.getQuestions().get(position).isAnswered());

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView questionText;
        private final TextView firstAnswer;
        private final TextView secondAnswer;
        private final TextView thirdAnswer;
        private final TextView fourthAnswer;
        private final TextView fifthAnswer;
        private final ImageView firstAnswerImage;
        private final ImageView secondAnswerImage;
        private final ImageView thirdAnswerImage;
        private final ImageView fourthAnswerImage;
        private final ImageView fifthAnswerImage;
        private final CardView cardView;
        private final View itemView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            questionText = (TextView) itemView.findViewById(R.id.cardQuestionText);

            cardView = (CardView) itemView.findViewById(R.id.cardView);

            firstAnswer = (TextView) itemView.findViewById(R.id.cardFirstAnswerText);
            secondAnswer = (TextView) itemView.findViewById(R.id.cardSecondAnswerText);
            thirdAnswer = (TextView) itemView.findViewById(R.id.cardThirdAnswerText);
            fourthAnswer = (TextView) itemView.findViewById(R.id.cardFourthAnswerText);
            fifthAnswer = (TextView) itemView.findViewById(R.id.cardFifthAnswerText);

            firstAnswerImage = (ImageView) itemView.findViewById(R.id.cardFirstAnswerImage);
            secondAnswerImage = (ImageView) itemView.findViewById(R.id.cardSecondAnswerImage);
            thirdAnswerImage = (ImageView) itemView.findViewById(R.id.cardThirdAnswerImage);
            fourthAnswerImage = (ImageView) itemView.findViewById(R.id.cardFourthAnswerImage);
            fifthAnswerImage = (ImageView) itemView.findViewById(R.id.cardFifthAnswerImage);
        }

        void setAnswerImage(Question question) {
            for (int i = 0; i < 5; i++) {
                if (question.getAnswers()[i].isCorrect()) {
                    if (question.getAnswers()[i].isSelected()) {
                        getAnswerImage(i).setImageResource(R.drawable.right);
                    } else {
                        getAnswerImage(i).setImageResource(R.drawable.wrong);
                    }
                }
            }
        }

        private void setDefaultAnswerImage() {
            firstAnswerImage.setImageDrawable(null);
            secondAnswerImage.setImageDrawable(null);
            thirdAnswerImage.setImageDrawable(null);
            fourthAnswerImage.setImageDrawable(null);
            fifthAnswerImage.setImageDrawable(null);
        }

        private ImageView getAnswerImage(int answer) {
            switch (answer) {
                case 0:
                    return firstAnswerImage;
                case 1:
                    return secondAnswerImage;
                case 2:
                    return thirdAnswerImage;
                case 3:
                    return fourthAnswerImage;
                case 4:
                    return fifthAnswerImage;
                default:
                    return null;
            }
        }

        private void setCardViewColor(Question question) {
            if (question.isAnswered()) {
                cardView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),
                        R.color.rightCardColor));
            } else {
                cardView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),
                        R.color.wrongCardColor));
            }
        }
    }
}
