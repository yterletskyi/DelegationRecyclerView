package io.jasperapps.delegationrecyclerviewadapter.core

import android.util.SparseArray
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class RecyclerDelegationAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val delegates: MutableList<AdapterDelegate<AdapterDelegate.Holder>> = ArrayList()
    private val itemTypeToDelegatesMap = SparseArray<AdapterDelegate<AdapterDelegate.Holder>>()
    private val items: MutableList<ModelItem> = ArrayList()

    private var recyclerView: RecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val delegate = itemTypeToDelegatesMap[viewType]
        return delegate.onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val delegate = itemTypeToDelegatesMap[holder.itemViewType]
        delegate.onBindViewHolder(holder as AdapterDelegate.Holder, items[position])
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val delegate = itemTypeToDelegatesMap[holder.itemViewType]
        delegate.onBindViewHolder(holder as AdapterDelegate.Holder, items[position], payloads)
    }

    override fun getItemCount(): Int = items.size

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        delegates.forEach { it.onDetachedFromRecyclerView(recyclerView) }
        this.recyclerView = null
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        itemTypeToDelegatesMap[holder.itemViewType].onViewHolderRecycled(holder)
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        val delegate = delegates
            .find { delegate -> delegate.isForViewType(item, position) }
            ?: throw NullPointerException("Delegate not found for item at position: $position")

        val viewType = delegate.getViewType()
        itemTypeToDelegatesMap[viewType] = delegate
        return viewType
    }

    fun setItems(items: List<ModelItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    fun <D : AdapterDelegate<*>> addDelegate(delegate: D) {
        delegates.add(delegate as AdapterDelegate<AdapterDelegate.Holder>)
    }
}

