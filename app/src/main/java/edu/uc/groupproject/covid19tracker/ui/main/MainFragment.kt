package edu.uc.groupproject.covid19tracker.ui.main

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import edu.uc.groupproject.covid19tracker.R
import edu.uc.groupproject.covid19tracker.dto.Cases
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class MainFragment : Fragment() {

    companion object {
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        super.onCreate(savedInstanceState)

        /**
         * Declare field and other necessary variables
         */
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        val recoveredTxt: TextView = view.findViewById(R.id.recovered_num)
        val confirmedTxt: TextView = view.findViewById(R.id.confirmed_num)
        val deathsTxt: TextView = view.findViewById(R.id.deaths_num)
        val countryBarChart: BarChart = view.findViewById(R.id.by_country_bar_graph)
        val xAxisLabels: ArrayList<String> = ArrayList()
        val confirmedValues = ArrayList<BarEntry>()
        val recoveredValues = ArrayList<BarEntry>()
        val deathValues = ArrayList<BarEntry>()
        val barDataSetList: ArrayList<IBarDataSet> = ArrayList()

        /**
         * Get overview data from MainViewModel and set 'Overview' section text
         */
        viewModel.gmData.observe(viewLifecycleOwner, Observer {
                globalData ->
                    recoveredTxt.text = globalData.totalRecovered
                    confirmedTxt.text = globalData.totalCases
                    deathsTxt.text = globalData.totalDeaths
        })

        fun setBarChartData(caseData: Cases) {
            /**
             * Loop through cases array and set confirmed array
             */
            for(x in 0 until 5) {
                try{
                    if(caseData.cases[x].contains(",")) {
                        val replaceCharInString = caseData.cases[x].replace(",", "")
                        val convertToFloat = replaceCharInString.toFloat()
                        confirmedValues.add(BarEntry(convertToFloat, x))
                    }else {
                        val totalCasesToFloat = caseData.cases[x].toFloat()
                        confirmedValues.add(BarEntry(totalCasesToFloat, x))
                    }
                } catch(e: IOException) {
                    e.printStackTrace()
                }
            }

            /**
             * Loop through cases array and set recovered array
             */
            for(x in 0 until 5) {
                try {
                    if (caseData.totalRecovered[x].toLowerCase(Locale.ROOT) != "n/a") {
                        if(caseData.totalRecovered[x].contains(",")) {
                            val replaceCharInString = caseData.totalRecovered[x].replace(",", "")
                            val convertToFloat = replaceCharInString.toFloat()
                            recoveredValues.add(BarEntry(convertToFloat, x))
                        }else {
                            val totalRecoveredToFloat = caseData.totalRecovered[x].toFloat()
                            recoveredValues.add(BarEntry(totalRecoveredToFloat, x))
                        }
                    }else {
                        recoveredValues.add(BarEntry(0f, x))
                    }
                } catch(e: IOException) {
                    e.printStackTrace()
                }
            }

            /**
             * Loop through cases array and set death array
             */
            for(x in 0 until 5) {
                try{
                    if(caseData.deaths[x].contains(",")) {
                        val replaceCharInString = caseData.deaths[x].replace(",", "")
                        val convertToFloat = replaceCharInString.toFloat()
                        deathValues.add(BarEntry(convertToFloat, x))
                    }else {
                        val totalDeathsToFloat = caseData.deaths[x].toFloat()
                        deathValues.add(BarEntry(totalDeathsToFloat, x))
                    }
                } catch(e: IOException) {
                    e.printStackTrace()
                }
            }

            /**
             * Loop through countries array and set to labels array
             */
            for(x in 0 until 5) {
                try{
                    xAxisLabels.add(caseData.countryName[x])
                } catch(e: IOException) {
                    e.printStackTrace()
                }
            }

            /**
             * create bar data sets
             */
            val confirmedSet = BarDataSet(confirmedValues, "Confirmed Cases")
            val recoveredSet = BarDataSet(recoveredValues, "Recovered Cases")
            val deathSet = BarDataSet(deathValues, "Death Cases")
            confirmedSet.color = Color.BLUE
            recoveredSet.color = Color.GREEN
            deathSet.color = Color.RED
            barDataSetList.add(confirmedSet)
            barDataSetList.add(recoveredSet)
            barDataSetList.add(deathSet)
            val data = BarData(xAxisLabels, barDataSetList)

            /**
             * Set axis layout and  design
             */
            val xAxis = countryBarChart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            val yAxisRight = countryBarChart.axisRight
            yAxisRight.setDrawLabels(false)

            /**
             * Set data to chart and UI of the chart
             */
            countryBarChart.data = data
            countryBarChart.setDescription("")
            countryBarChart.animateXY(5000,5000)
        }

        /**
         * Get cases data and call function that sets data to the bar graph
         */
        viewModel.cData.observe(viewLifecycleOwner, Observer {
                caseData ->
            setBarChartData(caseData)
//            setCountryListViewData(caseData)
        })

        return view
    }

}
