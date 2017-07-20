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
        private final TextView firstAnswer;
        private final TextView secondAnswer;
        private final TextView thirdAnswer;
        private final TextView fourthAnswer;
        private final TextView fifthAnswer;
        private final ImageView answerImage;
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
