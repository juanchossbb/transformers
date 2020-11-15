package com.aequilibrium.transformers.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.VisibleForTesting
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import com.aequilibrium.transformers.MainActivity
import com.aequilibrium.transformers.R
import com.aequilibrium.transformers.data.model.Transformer

class CreateEditFragment : Fragment(), View.OnClickListener{
    lateinit var tvTitle : TextView
    lateinit var etName : EditText
    lateinit var etStrength : EditText
    lateinit var etIntelligence : EditText
    lateinit var etSpeed : EditText
    lateinit var etEndurance : EditText
    lateinit var  etRank : EditText
    lateinit var etCourage : EditText
    lateinit var etFirepower : EditText
    lateinit var etSkill : EditText
    lateinit var spTeam : Spinner
    lateinit var btnSubmit : Button
    @VisibleForTesting
    val viewModel by lazy { CreateEditViewModel() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_create_edit,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            tvTitle = findViewById(R.id.tv_create_edit_title)
            etName = findViewById(R.id.et_name)
            etStrength = findViewById(R.id.et_strength)
            etIntelligence = findViewById(R.id.et_intelligence)
            etSpeed = findViewById(R.id.et_speed)
            etEndurance = findViewById(R.id.et_endurance)
            etRank = findViewById(R.id.et_rank)
            etCourage = findViewById(R.id.et_courage)
            etFirepower = findViewById(R.id.et_firepower)
            etSkill = findViewById(R.id.et_skill)
            spTeam = findViewById(R.id.sp_team)
            btnSubmit = findViewById(R.id.btn_submit)

            ArrayAdapter.createFromResource(
                spTeam.context,
                R.array.teams,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spTeam.adapter = adapter
            }

        }
        btnSubmit.setOnClickListener(this)

        viewModel.run {
            livedata.observeForever {
                Toast.makeText(activity, "Transformer Created", Toast.LENGTH_SHORT).show()
                (activity as MainActivity?)?.launchTransformerListFragment()
            }
                errorLiveData.observeForever {
                    (activity as MainActivity?)?.launchErrorAlertDialog(it)
                }
        }
    }

    companion object{
        val instance = CreateEditFragment()
    }

    override fun onClick(p0: View?) {
        if (validateFields()){
            val tranformer = Transformer().apply {
                setName(etName.text.toString())
                setStrength(etStrength.text.toString().toInt())
                setIntelligence(etIntelligence.text.toString().toInt())
                setSpeed(etSpeed.text.toString().toInt())
                setEndurance(etEndurance.text.toString().toInt())
                setRank(etRank.text.toString().toInt())
                setCourage(etCourage.text.toString().toInt())
                setFirepower(etFirepower.text.toString().toInt())
                setSkill(etSkill.text.toString().toInt())
                setTeam(if(spTeam.selectedItemPosition == 0) "A" else "D")
            }

            viewModel.createTransformer(tranformer)
        }
    }

    private fun resetErrorMessages(){
        etName.error = null
        etStrength.error = null
        etIntelligence.error = null
        etSpeed.error = null
        etEndurance.error = null
        etRank.error = null
        etCourage.error = null
        etFirepower.error = null
        etSkill.error = null
    }

    @VisibleForTesting
    fun validateFields() : Boolean{
        resetErrorMessages()
        when {
            etName.text.isEmpty() -> {
                etName.error = "This field should not be empty"
                return false
            }
            etStrength.text.isEmpty() -> {
                etStrength.error = "This field should not be empty"
                return false
            }
            !etStrength.text.isDigitsOnly() -> {
                etStrength.error = "This field should only contain numbers"
                return false
            }
            etIntelligence.text.isEmpty() -> {
                etIntelligence.error = "This field should not be empty"
                return false
            }
            !etIntelligence.text.isDigitsOnly() -> {
                etIntelligence.error = "This field should only contain numbers"
                return false
            }
            etSpeed.text.isEmpty() -> {
                etSpeed.error = "This field should not be empty"
                return false
            }
            !etSpeed.text.isDigitsOnly() -> {
                etSpeed.error = "This field should only contain numbers"
                return false
            }
            etEndurance.text.isEmpty() -> {
                etEndurance.error = "This field should not be empty"
                return false
            }
            !etEndurance.text.isDigitsOnly() -> {
                etEndurance.error = "This field should only contain numbers"
                return false
            }
            etRank.text.isEmpty() -> {
                etRank.error = "This field should not be empty"
                return false
            }
            !etRank.text.isDigitsOnly() -> {
                etRank.error = "This field should only contain numbers"
                return false
            }
            etCourage.text.isEmpty() -> {
                etCourage.error = "This field should not be empty"
                return false
            }
            !etCourage.text.isDigitsOnly() -> {
                etCourage.error = "This field should only contain numbers"
                return false
            }
            etFirepower.text.isEmpty() -> {
                etFirepower.error = "This field should not be empty"
                return false
            }
            !etFirepower.text.isDigitsOnly() -> {
                etFirepower.error = "This field should only contain numbers"
                return false
            }
            etSkill.text.isEmpty() -> {
                etSkill.error = "This field should not be empty"
                return false
            }
            !etSkill.text.isDigitsOnly() -> {
                etSkill.error = "This field should only contain numbers"
                return false
            }
            else -> return true
        }
    }

}