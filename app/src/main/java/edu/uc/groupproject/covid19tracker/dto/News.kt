package edu.uc.groupproject.covid19tracker.dto

import com.google.gson.annotations.SerializedName

data class News(@SerializedName("author") var author: ArrayList<String>,
                @SerializedName("title") var title: ArrayList<String>,
                @SerializedName("description") var description: ArrayList<String>,
                @SerializedName("url") var url: ArrayList<String>,
                @SerializedName("urlToImage") var urlToImage: ArrayList<String>,
                @SerializedName("publishedAt") var publishedAt: ArrayList<String>,
                @SerializedName("content") var content: ArrayList<String>)