package io.jasperapps.delegationrecyclerviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.jasperapps.delegationrecyclerviewadapter.core.AdapterDelegate
import io.jasperapps.delegationrecyclerviewadapter.core.ModelItem


class SampleTextDelegate1 : AdapterDelegate<SampleTextDelegate1.SampleTextViewHolder>() {

    class SampleTextViewHolder(view: View) : Holder(view) {
        val textView: TextView by lazy { view.findViewById(android.R.id.text1) }
    }

    override fun onCreateViewHolder(parent: ViewGroup): SampleTextViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false)
        return SampleTextViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: SampleTextViewHolder, item: ModelItem) {
        item as TextModelItem1

        viewHolder.textView.text = item.text
    }

    override fun isForViewType(item: ModelItem, position: Int): Boolean = item is TextModelItem1

    override fun getViewType(): Int = 1

}