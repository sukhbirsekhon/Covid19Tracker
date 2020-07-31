package edu.uc.groupproject.covid19tracker.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import edu.uc.groupproject.covid19tracker.R
import edu.uc.groupproject.covid19tracker.dto.News

class NewsFragment : Fragment() {

    companion object;

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        /**
         * Declare field and other necessary variables
         */
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        val newsListView: ListView = view.findViewById(R.id.news_list_view)
        val refreshView: SwipeRefreshLayout = view.findViewById(R.id.news_refresh_view)
        val progressBar: ProgressBar = view.findViewById(R.id.news_progress_bar)

        /**
         * Add refresh listen
         */
        refreshView.setOnRefreshListener {
            viewModel.globalData()
            refreshView.isRefreshing = false
        }

        fun setNewsListViewData(newsData: News) {
            /**
             * Declare news list array, loop through news data, and set data to list view
             */
            val newsListViewItems = ArrayList<News>()
            for(x in 0 until newsData.title.size) {
                newsListViewItems.add(
                    News(author = arrayListOf(newsData.author[x]), title = arrayListOf(newsData.title[x]), description = arrayListOf(newsData.description[x]),
                    url = arrayListOf(newsData.url[x]), urlToImage = arrayListOf(newsData.urlToImage[x]), publishedAt = arrayListOf(newsData.publishedAt[x]),
                    content = arrayListOf(newsData.title[x]))
                )
            }
            val arrAdapter = ItemAdapter2(view.context, newsListViewItems)
            newsListView.adapter = arrAdapter

            /**
             * set click listener to each list item - click will take user to news url
             */
            newsListView.setOnItemClickListener { parent, view, position, id ->
                val element: News = arrAdapter.getItem(position)
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(element.url[0])
                startActivity(openURL)
            }

            /**
             * set listener for search bar to filter through news list
             */
            val searchBar: EditText = view.findViewById(R.id.search_bar) as EditText
            searchBar.addTextChangedListener(object: TextWatcher {
                override fun afterTextChanged(s: Editable?) { }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    if(s.isEmpty()) {
                        newsListView.adapter = arrAdapter
                    }else {
                        val searchedNewsListViewItems = ArrayList<News>()
                        for(x in 0 until newsData.title.size) {
                            if(newsData.title[x].contains(s, true) || newsData.description[x].contains(s, true)) {
                                searchedNewsListViewItems.add(
                                    News(author = arrayListOf(newsData.author[x]), title = arrayListOf(newsData.title[x]), description = arrayListOf(newsData.description[x]),
                                        url = arrayListOf(newsData.url[x]), urlToImage = arrayListOf(newsData.urlToImage[x]), publishedAt = arrayListOf(newsData.publishedAt[x]),
                                        content = arrayListOf(newsData.title[x]))
                                )
                            }
                        }
                        val searchedArrAdapter = ItemAdapter2(
                            view.context,
                            searchedNewsListViewItems
                        )
                        newsListView.adapter = searchedArrAdapter
                    }
                }
            })
        }

        /**
         * Get news data and call function that sets data to the list view
         */
        viewModel.nData.observe(viewLifecycleOwner, Observer {
                newsData ->
            setNewsListViewData(newsData)
            progressBar.visibility = View.GONE
        })

        return view
    }

}