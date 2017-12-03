package com.jqk.loadmorerecyclerview

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.jqk.kotlinmvvm.loadmore.MyAdapter
import com.jqk.loadmorelibrary.LRecyclerView
import com.jqk.loadmorerecyclerview.databinding.ActivityLoadmoreBinding

/**
 * Created by Administrator on 2017/11/16 0016.
 */

class LoadmoreActivity : AppCompatActivity() {

    var binding: ActivityLoadmoreBinding? = null
    var datas: ArrayList<Data>? = null
    var mAdapter: MyAdapter? = null

    var loadNum: Int = 0

    var handler = object : Handler() {

        override fun handleMessage(msg: Message?) {
            if (msg!!.what == 1000) {
                if (loadNum == 2) {
                    binding!!.recyclerView.loadingFinish = true
                    binding!!.recyclerView!!.loadingFinish()
                } else {
                    for (i in 21..30) {
                        var data = Data()
                        data.title = "" + i
                        datas!!.add(data)
                    }
                    binding!!.recyclerView.notifyDataSetChanged()
                    binding!!.recyclerView.loadingFinish = false
                }
            }
            super.handleMessage(msg)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_loadmore)

        datas = ArrayList<Data>()
        for (i in 0..40) {
            var data = Data()
            data.title = "" + i
            datas!!.add(data)
        }

        binding!!.recyclerView.layoutManager = GridLayoutManager(this, 2)
        mAdapter = MyAdapter(datas!!)
        binding!!.recyclerView.adapter = mAdapter
        binding!!.recyclerView.setOnRefreshFinishListener(object : LRecyclerView.OnLoadmoreListener {
            override fun onLoadmore() {
                Log.d("123456", "加载更多")
                loadNum++
                handler.sendEmptyMessageDelayed(1000, 2000)
            }
        })
    }
}
