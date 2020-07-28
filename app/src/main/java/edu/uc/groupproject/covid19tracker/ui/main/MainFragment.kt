package edu.uc.groupproject.covid19tracker.ui.main

import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
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
import edu.uc.groupproject.covid19tracker.dto.StatesData
import edu.uc.groupproject.covid19tracker.service.StateDataProvider
import edu.uc.groupproject.covid19tracker.utility.StateNameToCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainFragment : Fragment() {

    private val LOCATION_PERMISSION_REQUEST_CODE = 2000
    private lateinit var locationViewModel: LocationViewModel

    private var lat: Double = 0.0
    private var long: Double = 0.0

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
        })
        prepRequestLocationUpdates()
        return view
    }

    private fun prepRequestLocationUpdates() {
        val permissionRequest = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
        requestPermissions(permissionRequest, LOCATION_PERMISSION_REQUEST_CODE)
    }

    fun requestLocationUpdate() {
        locationViewModel = ViewModelProvider(this).get(locationViewModel::class.java)

        locationViewModel.getLocationLiveData().observe(this, Observer {
            Log.d("Latitude", it.latitude)
            Log.d("Longitude", it.longitude)

            lat = it.latitude.toDouble()
            long = it.longitude.toDouble()

            retrieveStateCasesData()
        })
    }

    /**
     * Retrieve the state information like state name and based on the info, call the api and
     * retrieve covid 19 cases data of user locality.
     *
     */
    private fun retrieveStateCasesData() {
        try {
            var geoCoder = Geocoder(context)
            var addresses: List<Address>
            addresses = geoCoder.getFromLocation(lat, long, 1)

            var stateUtility = StateNameToCode()
            var states: HashMap<String, String> = stateUtility.convertStateNameToCode()

            var country: String = addresses.get(0).countryName
            var countryCode: String = addresses.get(0).countryCode
            var locality: String = addresses.get(0).locality
            var state: String = addresses.get(0).adminArea

            Log.d("Country name", country)
            Log.d("Country code", countryCode)
            Log.d("Locality", locality)
            Log.d("state", states.get(state))

            GlobalScope.launch(context = Dispatchers.Main) {
                val stateDataProvider = StateDataProvider()

                val date: ArrayList<Int>? = stateDataProvider.getStateData(states.get(state).toString().toLowerCase(), "date")
                val total: ArrayList<Int>? = stateDataProvider.getStateData(states.get(state).toString().toLowerCase(), "total")
                val positive: ArrayList<Int>? = stateDataProvider.getStateData(states.get(state).toString().toLowerCase(), "positive")
                val death: ArrayList<Int>? = stateDataProvider.getStateData(states.get(state).toString().toLowerCase(), "death")
                val hospitalizedCurrently: ArrayList<Int>? = stateDataProvider.getStateData(states.get(state).toString().toLowerCase(), "hospitalizedCurrently")
                delay(2000)

                if(!date.isNullOrEmpty()) {
                    var statesData = StatesData(date.get(0), total!!.get(0), positive!!.get(0), death!!.get(0), hospitalizedCurrently!!.get((0)))
                    Log.d("states date:", statesData.toString())
                }
                else {
                    Toast.makeText(context, "Unable to update user local data", Toast.LENGTH_LONG).show()
                }
            }
        } catch (e:Exception) {
            Toast.makeText(context, "Unable to update user local data", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
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
