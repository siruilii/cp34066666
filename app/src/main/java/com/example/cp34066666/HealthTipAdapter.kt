package com.example.cp34066666;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class HealthTipAdapter(private val context: Context, private val healthTips: List<HealthTip>) :
    BaseAdapter() {

    override fun getCount(): Int = healthTips.size

    override fun getItem(position: Int): Any = healthTips[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_health_tip, parent, false)
        val healthTip = healthTips[position]

        val ivIcon = view.findViewById<ImageView>(R.id.iv_icon)
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val tvDescription = view.findViewById<TextView>(R.id.tv_description)

        ivIcon.setImageResource(healthTip.imageResId)
        tvTitle.text = healthTip.title
        tvDescription.text = healthTip.description

        return view
    }
}