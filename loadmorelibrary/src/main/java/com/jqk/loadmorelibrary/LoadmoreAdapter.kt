package com.jqk.loadmorelibrary

import android.databinding.DataBindingUtil
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jqk.loadmorelibrary.databinding.ItemFooterBinding

/**
 * Created by Administrator on 2017/11/16 0016.
 */
class LoadmoreAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private var mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
    private var mRecylcerView: LRecyclerView
    var itemFooterBinding: ItemFooterBinding? = null
    var finish: Boolean = false

    constructor(mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, mRecylcerView: LRecyclerView) {
        this.mAdapter = mAdapter
        this.mRecylcerView = mRecylcerView

        if (mRecylcerView.layoutManager is GridLayoutManager) {
            (mRecylcerView.layoutManager as GridLayoutManager).spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {

                    val type = getItemViewType(position)

                    if (type == 1) {
                        return (mRecylcerView.layoutManager as GridLayoutManager).getSpanCount()
                    } else {
                        return 1
                    }
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return mAdapter.itemCount + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position < mAdapter.itemCount) {
            return 0
        } else {
            return 1
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        if (holder is FooterViewHolder) {
            if (finish) {
                finish()
            } else {
                progress()
            }
        } else {
            mAdapter.onBindViewHolder(holder, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

        if (viewType != 0) {
            val itemFooterBinding = DataBindingUtil.inflate<ItemFooterBinding>(LayoutInflater.from(parent!!.context), R.layout.item_footer, parent, false)
            this.itemFooterBinding = itemFooterBinding
            return FooterViewHolder(itemFooterBinding)
        } else {
            return mAdapter.onCreateViewHolder(parent, viewType)
        }
    }

    fun progress() {
        finish = false
        itemFooterBinding!!.progressView.visibility = View.VISIBLE
        itemFooterBinding!!.overView.visibility = View.INVISIBLE
    }

    fun finish() {
        finish = true
        itemFooterBinding!!.progressView.visibility = View.INVISIBLE
        itemFooterBinding!!.overView.visibility = View.VISIBLE
    }

    inner class FooterViewHolder(var itemFooterBinding: ItemFooterBinding) : RecyclerView.ViewHolder(itemFooterBinding.root) {

    }
}