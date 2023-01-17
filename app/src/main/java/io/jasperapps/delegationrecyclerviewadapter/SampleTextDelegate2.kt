package io.jasperapps.delegationrecyclerviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.jasperapps.delegationrecyclerviewadapter.core.AdapterDelegate
import io.jasperapps.delegationrecyclerviewadapter.core.ModelItem


class SampleTextDelegate2 : AdapterDelegate<SampleTextDelegate2.SampleTextViewHolder>() {

    class SampleTextViewHolder(view: View) : Holder(view) {
        val textView1: TextView by lazy { view.findViewById(android.R.id.text1) }
        val textView2: TextView by lazy { view.findViewById(android.R.id.text2) }
    }

    override fun onCreateViewHolder(parent: ViewGroup): SampleTextViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false)
        return SampleTextViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: SampleTextViewHolder, item: ModelItem) {
        item as TextModelItem2

        viewHolder.textView1.text = item.text1
        viewHolder.textView2.text = item.text2
    }

    override fun isForViewType(item: ModelItem, position: Int): Boolean = item is TextModelItem2

    override fun getViewType(): Int = 2

}