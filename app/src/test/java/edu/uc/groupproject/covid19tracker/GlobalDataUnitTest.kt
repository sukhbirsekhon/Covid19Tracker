package edu.uc.groupproject.covid19tracker

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import edu.uc.groupproject.covid19tracker.ui.main.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class GlobalDataUnitTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var mvm: MainViewModel

    @Before
    fun populateData() {
        mvm = MainViewModel()

    }

    @Test
    fun testForGlobalData() {
        mvm.globalData.observeForever {
            Assert.assertNotNull(it)
            Assert.assertTrue(it.size > 0)
            it.forEach {
                println("*****" + it)
            }
        }
    }
}