package by.test.belarussian.belarussiantests.fragments

import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import by.test.belarussian.belarussiantests.R
import by.test.belarussian.belarussiantests.bizlogic.qmodel.Questions

/**
 * This dialog is shown when user wants to start test with selected topic.
 *
 * @author Pavel Davydzenka
 */
class TopicsDialogFragment : DialogFragment(), AdapterView.OnItemClickListener {
    private lateinit var listView: ListView
    private lateinit var onTestStartListener: OnTestStartListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        onTestStartListener = context as OnTestStartListener
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        container!!.visibility = VISIBLE
        return inflater!!.inflate(R.layout.fragment_topics, container, false)
    }

    override fun onViewCreated(
            view: View?,
            savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        listView = view!!.findViewById(R.id.topicsListView) as ListView
        initListView(view)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val topic = p0!!.adapter.getItem(p2).toString()

        onTestStartListener.onTestStart(topic)
        activity.fragmentManager.beginTransaction().remove(this).commit()
    }

    private fun initListView(view: View?) {
        val topics = Questions.getInstance().sortedQuestions.questions.entries.map { it.key.toString() }
        listView.adapter = ArrayAdapter<String>(view!!.context, android.R.layout.simple_list_item_1, topics)
        listView.onItemClickListener = this
    }

    interface OnTestStartListener {
        fun onTestStart(topic: String)
    }
}