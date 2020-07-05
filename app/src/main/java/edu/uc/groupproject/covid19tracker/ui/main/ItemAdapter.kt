package edu.uc.groupproject.covid19tracker.ui.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import edu.uc.groupproject.covid19tracker.R
import edu.uc.groupproject.covid19tracker.dto.Cases

class ItemAdapter(var context: Context, var view_layout: Int, var arrayCaseList: ArrayList<Cases>): BaseAdapter() {
    override fun getItem(position: Int): Any {
        return arrayCaseList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayCaseList.size
    }

    override fun getView(position: Int, convertVire: View?, parent: ViewGroup?): View {
        val view: View = View.inflate(context, R.layout.country_list_view_layout, null)

        var title: TextView = view.findViewById<TextView>(R.id.list_country)
        var cases: TextView = view.findViewById<TextView>(R.id.list_cases)
        var recovered: TextView = view.findViewById<TextView>(R.id.list_recovered)
        var deaths: TextView = view.findViewById<TextView>(R.id.list_deaths)

        var items: Cases = arrayCaseList.get(position)
        title.text = items.countryName[0]
        cases.text = "Total Cases: " + items.cases[0]
        recovered.text = "Recovered Cases: " + items.totalRecovered[0]
        deaths.text = "Total Deaths: " + items.deaths[0]

        return view;
    }
}