package edu.uc.groupproject.covid19tracker.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import edu.uc.groupproject.covid19tracker.R
import edu.uc.groupproject.covid19tracker.dto.News
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList

class ItemAdapter2(private var context: Context, private var arrayNewsList: ArrayList<News>) :
    BaseAdapter() {

    override fun getItem(position: Int): News {
        return arrayNewsList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayNewsList.size
    }

    @SuppressLint("SetTextI18n", "ViewHolder")
    override fun getView(position: Int, convertVire: View?, parent: ViewGroup?): View {
        val view = View.inflate(context, R.layout.news_list_view_layout, null)

        val title: TextView = view.findViewById(R.id.news_title)
        val image: ImageView = view.findViewById(R.id.news_image)
        val author: TextView = view.findViewById(R.id.news_author)
        val newsContent: TextView = view.findViewById(R.id.news_content)
        val publishedAt: TextView = view.findViewById(R.id.news_publishedAt)

        val items: News = arrayNewsList[position]

        try {
            Picasso.with(context).load(items.urlToImage[0]).fit().centerCrop().into(image)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        title.text = items.title[0]
        newsContent.text = items.description[0]
        author.text = if (items.author[0] == "null") {
            "Author not provided"
        } else {
            items.author[0]
        }

        val date: String

        date = if (items.publishedAt[0].contains("T") && items.publishedAt[0].contains("Z")) {
            Instant.parse(items.publishedAt[0]).atOffset(ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern("MM-dd-yyyy h:mm a"))
        } else if (items.publishedAt[0].contains("T") && !items.publishedAt[0].contains("Z")) {
            ZonedDateTime.parse(items.publishedAt[0])
                .format(DateTimeFormatter.ofPattern("MM-dd-yyyy h:mm a"))
        } else {
            items.publishedAt[0]
        }

        publishedAt.text = "Published: $date"

        return view
    }
}