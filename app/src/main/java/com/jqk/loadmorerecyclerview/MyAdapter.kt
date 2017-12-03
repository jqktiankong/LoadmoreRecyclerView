package com.jqk.kotlinmvvm.loadmore

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jqk.loadmorerecyclerview.Data
import com.jqk.loadmorerecyclerview.R
import com.jqk.loadmorerecyclerview.databinding.ItemAdapterBinding

/**
 * Created by Administrator on 2017/11/16 0016.
 */
class MyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    var datas: List<Data>

    constructor(datas: List<Data>) : super() {
        this.datas = datas
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        (holder!! as ViewHolder).setData(datas.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

        val itemAdapterBinding = DataBindingUtil.inflate<ItemAdapterBinding>(LayoutInflater.from(parent!!.context), R.layout.item_adapter, parent, false)
        return ViewHolder(itemAdapterBinding)

    }

    inner class ViewHolder(var itemAdapterBinding: ItemAdapterBinding) : RecyclerView.ViewHolder(itemAdapterBinding.root) {

        fun setData(data: Data) {
            itemAdapterBinding.title.text = data.title
        }
    }

}