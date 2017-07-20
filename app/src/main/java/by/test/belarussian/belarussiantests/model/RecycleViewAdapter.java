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
        holder.answerImage.setImageResource(R.drawable.right);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView questionText;
        private final CheckBox firstAnswer;
        private final CheckBox secondAnswer;
        private final CheckBox thirdAnswer;
        private final CheckBox fourthAnswer;
        private final CheckBox fifthAnswer;
        private final ImageView answerImage;
        private Question question;

        public ItemViewHolder(View itemView) {
            super(itemView);
            question = null;
            questionText = (TextView) itemView.findViewById(R.id.cardQuestionText);
            firstAnswer = (CheckBox) itemView.findViewById(R.id.firstQuestionCheckbox);
            secondAnswer = (CheckBox) itemView.findViewById(R.id.secondQuestionCheckbox);
            thirdAnswer = (CheckBox) itemView.findViewById(R.id.thirdQuestionCheckbox);
            fourthAnswer = (CheckBox) itemView.findViewById(R.id.fourthQuestionCheckbox);
            fifthAnswer = (CheckBox) itemView.findViewById(R.id.fifthQuestionCheckbox);
            answerImage = getAnswerImageView(itemView);
        }

        // incorrect
        private ImageView getAnswerImageView(View itemView) {
            if (question.getAnswers()[0].isCorrect()) {
                return (ImageView) itemView.findViewById(R.id.cardFirstAnswerImage);
            } else if (question.getAnswers()[1].isCorrect()) {
                return (ImageView) itemView.findViewById(R.id.cardSecondAnswerImage);
            } else if (question.getAnswers()[2].isCorrect()) {
                return (ImageView) itemView.findViewById(R.id.cardThirdAnswerImage);
            } else if (question.getAnswers()[3].isCorrect()) {
                return (ImageView) itemView.findViewById(R.id.cardFourthAnswerImage);
            } else if (question.getAnswers()[4].isCorrect()) {
                return (ImageView) itemView.findViewById(R.id.cardFifthAnswerImage);
            }
            return null;
        }
    }
}
