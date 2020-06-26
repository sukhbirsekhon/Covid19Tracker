package edu.uc.groupproject.covid19tracker.ui.main

import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.*
import edu.uc.groupproject.covid19tracker.R
import edu.uc.groupproject.covid19tracker.dto.GlobalData

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        val recoveredTxt: TextView = view.findViewById<TextView>(R.id.recovered_num) as TextView
        val confirmedTxt: TextView = view.findViewById<TextView>(R.id.confirmed_num) as TextView
        val deathsTxt: TextView = view.findViewById<TextView>(R.id.deaths_num) as TextView
        val totalGraph: GraphView = view.findViewById<GraphView>(R.id.total_graph) as GraphView
        val countryBarChart: BarChart = view.findViewById<BarChart>(R.id.by_country_bar_graph) as BarChart
        val xAxis: XAxis = countryBarChart.xAxis

//        val xAxisLabels: ArrayList<String> = ArrayList()
//        xAxisLabels.add("United States")
//        xAxisLabels.add("Spain")
//        xAxis.valueFormatter = IAxisValueFormatter { value, axis ->
////            xAxisLabels.get(value.toInt())
//            if (xAxisLabels != null && value.toInt() >= 0
//                && value.toInt() <= xAxisLabels!!.size - 1) {
//                xAxisLabels!![value.toInt()].toString()
//            } else {
//                ("").toString()
//            }
//        } as ValueFormatter

        val dataSets: ArrayList<IBarDataSet> = ArrayList<IBarDataSet>()
        val values2 = ArrayList<BarEntry>()
        values2.add(BarEntry(2f, 2f))
        values2.add(BarEntry(3f, 3f))


        val set = BarDataSet(values2, "Countries")
        dataSets.add(set)
        val data = BarData(dataSets)

        countryBarChart.data = data


        totalGraph.title  = "Total Cases / Month"
        totalGraph.viewport.isXAxisBoundsManual =  true
        totalGraph.viewport.isYAxisBoundsManual = true

        val series = LineGraphSeries<DataPoint>()
        series.appendData(DataPoint(0.0,0.0), true, 500)
        series.appendData(DataPoint(1.0,1.0), true, 500)
        totalGraph.addSeries(series)

        viewModel.gmData.observe(viewLifecycleOwner, Observer {
                globalData ->
                    recoveredTxt.text = globalData.totalRecovered
                    confirmedTxt.text = globalData.totalCases
                    deathsTxt.text = globalData.totalDeaths
        })

        return view
    }

}
