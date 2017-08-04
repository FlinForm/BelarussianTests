package by.test.belarussian.belarussiantests.model.rvadapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.model.questions.Question;
import by.test.belarussian.belarussiantests.model.Quiz;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ItemViewHolder> {
    private final List<Question> questions;
    private final AdapterUtils adapterUtils;

    public RecycleViewAdapter(List<Question> questions) {
        this.questions = questions;
        adapterUtils = new AdapterUtils();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        adapterUtils.setDefaultAnswerImage(holder.imageViews);
        holder.questionText.setText(questions.get(position).getQuestion());
        holder.textViews[0].setText(questions.get(position).getAnswers()[0].getAnswer());
        holder.textViews[1].setText(questions.get(position).getAnswers()[1].getAnswer());
        holder.textViews[2].setText(questions.get(position).getAnswers()[2].getAnswer());
        holder.textViews[3].setText(questions.get(position).getAnswers()[3].getAnswer());
        holder.textViews[4].setText(questions.get(position).getAnswers()[4].getAnswer());
        adapterUtils.setAnswerImage(questions.get(position), holder.imageViews);
        adapterUtils.setCardViewColor(Quiz.getTestQuestions().get(position),
                holder.cardView,
                holder.itemView);
        adapterUtils.strikeThroughTextViews(questions.get(position), holder.textViews);
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

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            questionText = (TextView) itemView.findViewById(R.id.cardQuestionText);

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
    }
}
