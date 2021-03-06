package com.aequilibrium.transformers

import android.view.View
import android.widget.TextView
import com.aequilibrium.transformers.createedit.CreateEditFragment
import com.aequilibrium.transformers.data.model.Transformer
import com.aequilibrium.transformers.list.ListFragment
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric
import org.robolectric.shadows.ShadowAlertDialog


class MainActivityTest : BaseTest(){
    lateinit var activity : MainActivity

    @Before
    fun setUp(){
        activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .start()
            .resume()
            .get()
    }

    @Test
    fun checkViewModelTest(){
        assertNotNull("ViewModel should not be null", activity.viewModel)
        assertEquals("ViewModel should be of type MainActivityViewModel", MainActivityViewModel::class.java, activity.viewModel::class.java)
    }

    @Test
    fun checkActivityTest(){
        assertNotNull("Activity should not be null", activity)
    }

    @Test
    fun createFragmentLaunchedTest(){
        assertTrue("Activity should not have a fragment launched",activity.supportFragmentManager.fragments.isEmpty())
        activity.launchCreateFragment()
        activity.supportFragmentManager.executePendingTransactions()
        assertEquals("Activity should have one fragment attached",1,activity.supportFragmentManager.fragments.size)
        assertEquals("Fragment should be of type CreateEditFragment",
            CreateEditFragment::class.java,activity.supportFragmentManager.fragments[0]::class.java)
    }

    @Test
    fun editFragmentLaunchedTest(){
        assertTrue("Activity should not have a fragment launched",activity.supportFragmentManager.fragments.isEmpty())
        activity.launchEditFragment(Transformer().apply {
            setName("transformer name")
            setTeam("A")
        })
        activity.supportFragmentManager.executePendingTransactions()
        assertEquals("Activity should have one fragment attached",1,activity.supportFragmentManager.fragments.size)
        assertEquals("Fragment should be of type CreateEditFragment",
                CreateEditFragment::class.java,activity.supportFragmentManager.fragments[0]::class.java)
    }

    @Test
    fun transformerListFragmentLaunchedTest(){
        assertTrue("Activity should not have a fragment launched",activity.supportFragmentManager.fragments.isEmpty())
        activity.launchTransformerListFragment()
        activity.supportFragmentManager.executePendingTransactions()
        assertEquals("Activity should have one fragment attached",1,activity.supportFragmentManager.fragments.size)
        assertEquals("Fragment should be of type ListFragment",
                ListFragment::class.java,activity.supportFragmentManager.fragments[0]::class.java)
    }

    @Test
    fun launchErrorAlertDialogTest(){
        activity.launchErrorAlertDialog("error message")
        val dialog = ShadowAlertDialog.getLatestDialog()
        val dialogMessageView = dialog?.findViewById<TextView>(android.R.id.message)
        assertNotNull("message view should not be null",dialogMessageView)
        assertEquals("Alert dialog message should be the same", "error message",dialogMessageView?.text.toString())
    }

    @Test
    fun showProgressBarTest(){
        activity.showProgressBar(false)
        assertEquals("progressbar should be hidden", View.GONE,activity.progressBar.visibility)
        activity.showProgressBar(true)
        assertEquals("progressbar should be showing", View.VISIBLE,activity.progressBar.visibility)
    }
}