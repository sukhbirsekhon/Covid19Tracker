package edu.uc.groupproject.covid19tracker.ui.main

import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
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
import edu.uc.groupproject.covid19tracker.dto.GlobalData
import edu.uc.groupproject.covid19tracker.service.CasesByCountryDataProvider
import edu.uc.groupproject.covid19tracker.service.GlobalDataProvider
import kotlinx.android.synthetic.main.main_fragment.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class MainFragment : Fragment() {

    private val LOCATION_PERMISSION_REQUEST_CODE = 2000
    private lateinit var locationViewModel: LocationViewModel

    private var lat: Double = 0.0
    private var long: Double = 0.0

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        locationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        super.onCreate(savedInstanceState)

        /**
         * Declare field and other necessary variables
         */
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        val recoveredTxt: TextView = view.findViewById(R.id.recovered_num) as TextView
        val confirmedTxt: TextView = view.findViewById(R.id.confirmed_num) as TextView
        val deathsTxt: TextView = view.findViewById(R.id.deaths_num) as TextView
        val countryBarChart: BarChart = view.findViewById(R.id.by_country_bar_graph) as BarChart
//        val countryListView: ListView = view.findViewById(R.id.country_list_view) as ListView
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
                    val c = caseData.cases[x].replace(",", "").toFloat()
                    confirmedValues.add(BarEntry(c, x))
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
                            val c = caseData.totalRecovered[x].replace(",", "").toFloat()
                            recoveredValues.add(BarEntry(c, x))
                        }else {
                            val c = caseData.totalRecovered[x].toFloat()
                            recoveredValues.add(BarEntry(c, x))
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
                    val c = caseData.deaths[x].replace(",", "").toFloat()
                    deathValues.add(BarEntry(c, x))
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

//        fun setCountryListViewData(caseData: Cases) {
//            val casesListViewItems = ArrayList<Cases>()
//            for(x in 5 until caseData.countryName.size) {
//                casesListViewItems.add(Cases(cases = arrayListOf(caseData.cases[x]), deaths = arrayListOf(caseData.deaths[x]),
//                    totalRecovered = arrayListOf(caseData.totalRecovered[x]), activeCases = arrayListOf(caseData.activeCases[x]),
//                    newCases = arrayListOf(caseData.newCases[x]), countryName = arrayListOf(caseData.countryName[x]), newDeaths = arrayListOf(caseData.newDeaths[x]),
//                    seriousCritical = arrayListOf(caseData.seriousCritical[x]), totalCasesPerMillionPopulation = arrayListOf(caseData.totalCasesPerMillionPopulation[x])))
//            }
//            val arrAdapter = ItemAdapter(view.context, android.R.layout.simple_list_item_1, casesListViewItems)
//            countryListView.adapter = arrAdapter
//        }

        /**
         * Get cases data and call function that sets data to the bar graph
         */
        viewModel.cData.observe(viewLifecycleOwner, Observer {
                caseData ->
            setBarChartData(caseData)
//            setCountryListViewData(caseData)
        })
        prepRequestLocationUpdates()
        return view
    }

    private fun prepRequestLocationUpdates() {
        val permissionRequest = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
        requestPermissions(permissionRequest, LOCATION_PERMISSION_REQUEST_CODE)
    }

    private fun requestLocationUpdate() {
        locationViewModel = ViewModelProvider(this).get(locationViewModel::class.java)

        locationViewModel.getLocationLiveData().observe(this, Observer {
            Log.d("Latitude", it.latitude)
            Log.d("Longitude", it.longitude)
            lblLatitudeValue.text = it.latitude
            lblLongitudeValue.text = it.longitude
            lat = it.latitude.toDouble()
            long = it.longitude.toDouble()

            var geoCoder: Geocoder = Geocoder(context)
            var addresses: List<Address>
            addresses = geoCoder.getFromLocation(lat, long, 1)

            var country: String = addresses.get(0).countryName
            var countryCode: String = addresses.get(0).countryCode
            var locality: String = addresses.get(0).locality

            Log.d("Country name", country)
            Log.d("Country code", countryCode)
            Log.d("Locality", locality)
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] ==  PackageManager.PERMISSION_GRANTED) {
                    requestLocationUpdate()
                }
                else {
                    Toast.makeText(context, "Unable to update location without permission", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}
