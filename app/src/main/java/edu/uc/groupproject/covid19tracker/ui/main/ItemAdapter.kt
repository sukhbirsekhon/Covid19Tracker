package edu.uc.groupproject.covid19tracker.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import edu.uc.groupproject.covid19tracker.R
import edu.uc.groupproject.covid19tracker.dto.Cases

class ItemAdapter(private var context: Context, private var arrayCaseList: ArrayList<Cases>): BaseAdapter() {

    override fun getItem(position: Int): Any {
        return arrayCaseList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayCaseList.size
    }

    @SuppressLint("SetTextI18n", "ViewHolder")
    override fun getView(position: Int, convertVire: View?, parent: ViewGroup?): View {
        val view = View.inflate(context, R.layout.country_list_view_layout, null)

        val title: TextView = view.findViewById(R.id.list_country)
        val cases: TextView = view.findViewById(R.id.list_cases)
        val recovered: TextView = view.findViewById(R.id.list_recovered)
        val deaths: TextView = view.findViewById(R.id.list_deaths)

        val items: Cases = arrayCaseList[position]

        title.text = items.countryName[0]
        cases.text = "Total Cases: " + items.cases[0]
        recovered.text = "Recovered Cases: " + items.totalRecovered[0]
        deaths.text = "Total Deaths: " + items.deaths[0]

        return view
    }
}