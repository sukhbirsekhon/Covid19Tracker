package edu.uc.groupproject.covid19tracker.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import edu.uc.groupproject.covid19tracker.R
import edu.uc.groupproject.covid19tracker.dto.Cases


class ByCountryFragment : Fragment() {

    companion object {
        fun newInstance() = ByCountryFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        /**
         * Declare field and other necessary variables
         */
        val view = inflater.inflate(R.layout.fragment_by_country, container, false)
        val countryListView: ListView = view.findViewById(R.id.country_list_view)
        val countrySpinner: Spinner = view.findViewById(R.id.country_spinner)
        val refreshView: SwipeRefreshLayout = view.findViewById(R.id.by_country_refresh_view)
        val progressBar: ProgressBar = view.findViewById(R.id.cases_by_country_progress_bar)

        /**
         * Add refresh listen
         */
        refreshView.setOnRefreshListener {
            viewModel.globalData()
            refreshView.isRefreshing = false
        }

        fun setCountryListViewData(caseData: Cases) {
            val casesListViewItems = ArrayList<Cases>()
            val spinnerItemArray = ArrayList<String>()
            spinnerItemArray.add("All Countries")
            for(x in 0 until caseData.countryName.size) {
                spinnerItemArray.add(caseData.countryName[x])
                casesListViewItems.add(
                    Cases(cases = arrayListOf(caseData.cases[x]), deaths = arrayListOf(caseData.deaths[x]),
                    totalRecovered = arrayListOf(caseData.totalRecovered[x]), activeCases = arrayListOf(caseData.activeCases[x]),
                    newCases = arrayListOf(caseData.newCases[x]), countryName = arrayListOf(caseData.countryName[x]), newDeaths = arrayListOf(caseData.newDeaths[x]),
                    seriousCritical = arrayListOf(caseData.seriousCritical[x]), totalCasesPerMillionPopulation = arrayListOf(caseData.totalCasesPerMillionPopulation[x]))
                )
            }

            /**
             * Declare custom array adapter and set list view
             */
            val casesListViewAdapter = ItemAdapter(view.context, casesListViewItems)
            countryListView.adapter = casesListViewAdapter

            /**
             * Declare array adapter and set spinner values
             */
            val spinnerItemAdapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, spinnerItemArray)
            countrySpinner.adapter = spinnerItemAdapter

            /**
             * Add onItemSelectedListen to spinner
             */
            countrySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                /**
                 * set onItemSelected to display selected country
                 */
                override fun onItemSelected(parent: AdapterView<*>?, adapterView: View?,
                                            position: Int, id: Long) {
                    val element = spinnerItemAdapter.getItem(position)
                    if(element == "All Countries") {
                        countryListView.adapter = casesListViewAdapter
                    }else {
                        val newCasesListViewItems = ArrayList<Cases>()
                        for(x in 0 until caseData.countryName.size) {
                            if(caseData.countryName[x] == element){
                                newCasesListViewItems.add(
                                    Cases(cases = arrayListOf(caseData.cases[x]), deaths = arrayListOf(caseData.deaths[x]),
                                        totalRecovered = arrayListOf(caseData.totalRecovered[x]), activeCases = arrayListOf(caseData.activeCases[x]),
                                        newCases = arrayListOf(caseData.newCases[x]), countryName = arrayListOf(caseData.countryName[x]), newDeaths = arrayListOf(caseData.newDeaths[x]),
                                        seriousCritical = arrayListOf(caseData.seriousCritical[x]), totalCasesPerMillionPopulation = arrayListOf(caseData.totalCasesPerMillionPopulation[x]))
                                )
                            }
                        }
                        val newArrAdapter = ItemAdapter(view.context, newCasesListViewItems)
                        countryListView.adapter = newArrAdapter
                    }
                }

            }
        }


        /**
         * Get cases data and call function that sets data to the list view
         */
        viewModel.cData.observe(viewLifecycleOwner, Observer {
                caseData ->
            setCountryListViewData(caseData)
            progressBar.visibility = View.GONE
        })

        return view
    }

}