package edu.uc.groupproject.covid19tracker

import edu.uc.groupproject.covid19tracker.service.NewsDataProvider
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * Tests confirming data is being received from News data API
 */
class NewsDataProviderTests {

    @Test
    fun retrieveNewsDataApiTest_author() {
        var newsData = NewsDataProvider()
        val author: ArrayList<String>? = newsData.getNewsData("author")
        Thread.sleep(2000)
        assertNotEquals(author!!.size, 0)
    }

    @Test
    fun retrieveNewsDataApiTest_title() {
        var newsData = NewsDataProvider()
        val title: ArrayList<String>? = newsData.getNewsData("title")
        Thread.sleep(2000)
        assertNotEquals(title!!.size, 0)
    }

    @Test
    fun retrieveNewsDataApiTest_description() {
        var newsData = NewsDataProvider()
        val description: ArrayList<String>? = newsData.getNewsData("description")
        Thread.sleep(2000)
        assertNotEquals(description!!.size, 0)
    }

    @Test
    fun retrieveNewsDataApiTest_url() {
        var newsData = NewsDataProvider()
        val url: ArrayList<String>? = newsData.getNewsData("url")
        Thread.sleep(2000)
        assertNotEquals(url!!.size, 0)
    }

    @Test
    fun retrieveNewsDataApiTest_urlToImage() {
        var newsData = NewsDataProvider()
        val urlToImage: ArrayList<String>? = newsData.getNewsData("urlToImage")
        Thread.sleep(2000)
        assertNotEquals(urlToImage!!.size, 0)
    }

    @Test
    fun retrieveNewsDataApiTest_publishedAt() {
        var newsData = NewsDataProvider()
        val publishedAt: ArrayList<String>? = newsData.getNewsData("publishedAt")
        Thread.sleep(2000)
        assertNotEquals(publishedAt!!.size, 0)
    }

    @Test
    fun retrieveNewsDataApiTest_content() {
        var newsData = NewsDataProvider()
        val content: ArrayList<String>? = newsData.getNewsData("content")
        Thread.sleep(2000)
        assertNotEquals(content!!.size, 0)
    }


}

