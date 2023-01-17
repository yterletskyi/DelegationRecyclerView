package io.jasperapps.delegationrecyclerviewadapter.core

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class AdapterDelegate<VH : AdapterDelegate.Holder> {

    open class Holder(view: View) : RecyclerView.ViewHolder(view)

    abstract fun onCreateViewHolder(parent: ViewGroup): VH

    abstract fun onBindViewHolder(viewHolder: VH, item: ModelItem)

    abstract fun isForViewType(item: ModelItem, position: Int): Boolean

    abstract fun getViewType(): Int

    open fun onBindViewHolder(
        viewHolder: VH,
        item: ModelItem,
        payloads: MutableList<Any>
    ) {
        onBindViewHolder(viewHolder, item)
    }

    open fun onViewHolderCreated(viewHolder: VH) {}

    open fun onViewHolderRecycled(viewHolder: RecyclerView.ViewHolder) {}

    open fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {}
}