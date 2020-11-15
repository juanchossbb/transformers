package com.aequilibrium.transformers

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.aequilibrium.transformers.createedit.CreateEditFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var fab : FloatingActionButton
    @VisibleForTesting
    val viewModel by lazy { MainActivityViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        fab = findViewById(R.id.fab)
    }

    override fun onStart() {
        super.onStart()

        fab.setOnClickListener {
            launchCreateFragment()
        }
        getToken()
    }

    private fun getToken(){
        viewModel.run {
            viewModel.getToken()
            livedata.observeForever {
                launchTransformerListFragment()
            }
            errorLiveData.observeForever {
                launchErrorAlertDialog(it)
            }
        }
    }

    @VisibleForTesting
    fun launchCreateFragment(){
        fab.visibility = View.GONE
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, CreateEditFragment.instance).addToBackStack(null).commit()
    }

    @VisibleForTesting
    fun launchTransformerListFragment(){
        //TODO
    }

    @VisibleForTesting
    fun launchErrorAlertDialog(message : String){
    AlertDialog.Builder(this).apply {
            setTitle("An error has occurred")
            setMessage(message)
            show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}