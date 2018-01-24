package by.test.belarussian.belarussiantests.bizlogic.rvadapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.bizlogic.qmodel.Question;

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
        holder.checkBoxes.get(0).setText(questions.get(position).getAnswers()[0].getAnswer());
        holder.checkBoxes.get(1).setText(questions.get(position).getAnswers()[1].getAnswer());
        holder.checkBoxes.get(1).setText(questions.get(position).getAnswers()[1].getAnswer());
        holder.checkBoxes.get(2).setText(questions.get(position).getAnswers()[2].getAnswer());
        holder.checkBoxes.get(2).setText(questions.get(position).getAnswers()[2].getAnswer());
        holder.checkBoxes.get(3).setText(questions.get(position).getAnswers()[3].getAnswer());
        holder.checkBoxes.get(3).setText(questions.get(position).getAnswers()[3].getAnswer());
        holder.checkBoxes.get(4).setText(questions.get(position).getAnswers()[4].getAnswer());
        holder.checkBoxes.get(4).setText(questions.get(position).getAnswers()[4].getAnswer());
        adapterUtils.setAnswerImage(questions.get(position), holder.imageViews);
        adapterUtils.setCardViewColor(questions.get(position), holder.cardView, holder.itemView);
        adapterUtils.setCheckBoxes(questions.get(position), holder.checkBoxes);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.cardView) CardView cardView;
        @BindView(R.id.cardQuestionText) TextView questionText;
        @BindViews({
                R.id.cardFirstAnswerCheckbox,
                R.id.cardSecondAnswerCheckbox,
                R.id.cardThirdAnswerCheckbox,
                R.id.cardFourthAnswerCheckbox,
                R.id.cardFifthAnswerCheckbox}) List<CheckBox> checkBoxes;
        @BindViews({
                R.id.cardFirstAnswerImage,
                R.id.cardSecondAnswerImage,
                R.id.cardThirdAnswerImage,
                R.id.cardFourthAnswerImage,
                R.id.cardFifthAnswerImage}) List<ImageView> imageViews;
        private View itemView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}