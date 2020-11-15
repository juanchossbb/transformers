package com.aequilibrium.transformers

import android.os.Bundle
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.aequilibrium.transformers.createedit.CreateEditFragment
import com.aequilibrium.transformers.data.model.Transformer
import com.aequilibrium.transformers.list.ListFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), MainActivityInterface {
    lateinit var fab : FloatingActionButton
    lateinit var progressBar : ConstraintLayout
    @VisibleForTesting
    val viewModel by lazy { MainActivityViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        fab = findViewById(R.id.fab)
        progressBar = findViewById(R.id.cl_progress_bar)
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
            livedata.observe(this@MainActivity, {
                launchTransformerListFragment()
            })
            errorLiveData.observe(this@MainActivity, {
                launchErrorAlertDialog(it)
            })
        }
    }

    override fun launchCreateFragment(){
        fab.visibility = View.GONE
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, CreateEditFragment.getCreateFragment(this)).addToBackStack(null).commit()
    }

    override fun launchTransformerListFragment(){
        fab.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ListFragment.getInstance(this)).commit()
    }

    override fun launchEditFragment(transformer: Transformer){
        fab.visibility = View.GONE
        val fragment = CreateEditFragment.getEditFragment(transformer, this)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit()
    }

    override fun showProgressBar(show : Boolean){
        if(show){
            progressBar.visibility = View.VISIBLE
        }else{
            progressBar.visibility = View.GONE
        }
    }

    override fun launchErrorAlertDialog(message : String){
    AlertDialog.Builder(this).apply {
            setTitle(R.string.error_dialog_title)
            setMessage(message)
            setPositiveButton(R.string.error_dialog_button) { p0, p1 -> p0.dismiss() }
        show()
        }
    }
}