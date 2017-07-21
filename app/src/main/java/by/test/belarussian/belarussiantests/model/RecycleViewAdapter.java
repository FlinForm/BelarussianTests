package by.test.belarussian.belarussiantests.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
        holder.question = questions.get(position);
        holder.questionText.setText(questions.get(position).getQuestion());
        holder.firstAnswer.setText(questions.get(position).getAnswers()[0].getAnswer());
        holder.secondAnswer.setText(questions.get(position).getAnswers()[1].getAnswer());
        holder.thirdAnswer.setText(questions.get(position).getAnswers()[2].getAnswer());
        holder.fourthAnswer.setText(questions.get(position).getAnswers()[3].getAnswer());
        holder.fifthAnswer.setText(questions.get(position).getAnswers()[4].getAnswer());
        holder.setAnswerImage();
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
        private ImageView firstAnswerImage;
        private ImageView secondAnswerImage;
        private ImageView thirdAnswerImage;
        private ImageView fourthAnswerImage;
        private ImageView fifthAnswerImage;
        private Question question;

        public ItemViewHolder(View itemView) {
            super(itemView);
            question = null;
            questionText = (TextView) itemView.findViewById(R.id.cardQuestionText);

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

        protected void setAnswerImage() {
            if (question.getAnswers()[0].isCorrect()) {
                System.out.println(1);
                if (question.getAnswers()[0].isSelected()) {
                    System.out.println(2);
                    firstAnswerImage.setImageResource(R.drawable.right);
                }
            }
            if (question.getAnswers()[1].isCorrect()) {
                if (question.getAnswers()[1].isSelected()) {
                    secondAnswerImage.setImageResource(R.drawable.right);
                }
            }
            if (question.getAnswers()[2].isCorrect()) {
                if (question.getAnswers()[2].isSelected()) {
                    thirdAnswerImage.setImageResource(R.drawable.right);
                }
            }
            if (question.getAnswers()[3].isCorrect()) {
                if (question.getAnswers()[3].isSelected()) {
                    fourthAnswerImage.setImageResource(R.drawable.right);
                }
            }
            if (question.getAnswers()[4].isCorrect()) {
                if (question.getAnswers()[4].isSelected()) {
                    fifthAnswerImage.setImageResource(R.drawable.right);
                }
            }
        }
    }
}
