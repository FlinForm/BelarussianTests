@file:JvmName("AdapterUtils")

package by.test.belarussian.belarussiantests.bizlogic.utils

import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import by.test.belarussian.belarussiantests.R
import by.test.belarussian.belarussiantests.bizlogic.qmodel.Question

/**
 * File with utility methods for [RecycleViewAdapter]
 *
 * @author Pavel Davydzenka
 */
fun setAnswerImage(question: Question, imageViews: List<ImageView>) {
    for (i in imageViews.indices) {
        if (question.answers[i].isCorrect) {
            if (question.answers[i].isSelected) {
                imageViews[i].setImageResource(R.drawable.right)
            } else {
                imageViews[i].setImageResource(R.drawable.wrong)
            }
        }
    }
}

fun resetAnswerImage(imageViews: List<ImageView>) {
    for (imageView in imageViews) {
        imageView.setImageDrawable(null)
    }
}

fun setCardViewColor(question: Question, cardView: CardView, itemView: View) {
    if (question.isAnswered) cardView.background = ContextCompat.getDrawable(
            itemView.context,
            R.drawable.border_green)
    else cardView.background = ContextCompat.getDrawable(
            itemView.context,
            R.drawable.border_red)
}

fun setCheckBoxes(question: Question, checkBoxes: List<CheckBox>) {
    resetCheckBoxes(checkBoxes)
    for (i in 0 until question.answers.size) {
        checkBoxes[i].isClickable = false
        if (question.answers[i].isSelected) checkBoxes[i].isChecked = true
    }
}

private fun resetCheckBoxes(checkBoxes: List<CheckBox>) {
    for (checkBox in checkBoxes) {
        checkBox.isChecked = false
    }
}