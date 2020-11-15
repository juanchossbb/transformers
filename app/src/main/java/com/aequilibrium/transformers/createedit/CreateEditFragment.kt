package com.aequilibrium.transformers.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.VisibleForTesting
import androidx.core.os.bundleOf
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import com.aequilibrium.transformers.MainActivityInterface
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
    var editTransformer : Transformer? = null
    var mainInterface: MainActivityInterface? = null
    var isEditing = false
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

        (arguments?.get(Transformer::class.java.canonicalName) as? Transformer)?.let {
           fillFieldsForEditing(it)
        }
        btnSubmit.setOnClickListener(this)

        viewModel.run {
            livedata.observe(requireActivity(), {
                Toast.makeText(activity, if (isEditing) R.string.transformer_created else R.string.transformer_updated, Toast.LENGTH_SHORT).show()
                mainInterface?.run {
                    showProgressBar(false)
                    launchTransformerListFragment()
                }
            })

            errorLiveData.observe(requireActivity(), {
                mainInterface?.run {
                    showProgressBar(false)
                    launchErrorAlertDialog(it)
                }
            })
        }
    }

    @VisibleForTesting
    fun fillFieldsForEditing(transformer: Transformer){
        editTransformer = transformer
        tvTitle.text = getString(R.string.edit_transformer)
        etName.setText(transformer.getName())
        etStrength.setText(transformer.getStrength())
        etIntelligence.setText(transformer.getIntelligence())
        etSpeed.setText(transformer.getSpeed())
        etEndurance.setText(transformer.getEndurance())
        etRank.setText(transformer.getRank())
        etCourage.setText(transformer.getRank())
        etFirepower.setText(transformer.getFirepower())
        etSkill.setText(transformer.getSkill())
        val team = transformer.getTeam()
        if (team == getString(R.string.a)){
            spTeam.setSelection(0)
        }else{
            spTeam.setSelection(1)
        }
        btnSubmit.text = getString(R.string.edit_transformer)
        isEditing = true
    }

    companion object{
        fun getCreateFragment(mainInterface : MainActivityInterface) : CreateEditFragment{
            return CreateEditFragment().apply { this.mainInterface = mainInterface  }
        }
        fun getEditFragment(transformer: Transformer, mainInterface : MainActivityInterface) : CreateEditFragment{
            return CreateEditFragment().apply {
                arguments = bundleOf(Transformer::class.java.canonicalName to transformer)
                this.mainInterface = mainInterface
            }
        }
    }

    override fun onClick(p0: View?) {
        if (validateFields()){
            mainInterface?.showProgressBar(true)
            val transformer = Transformer().apply {
                setName(etName.text.toString())
                setStrength(etStrength.text.toString().toInt())
                setIntelligence(etIntelligence.text.toString().toInt())
                setSpeed(etSpeed.text.toString().toInt())
                setEndurance(etEndurance.text.toString().toInt())
                setRank(etRank.text.toString().toInt())
                setCourage(etCourage.text.toString().toInt())
                setFirepower(etFirepower.text.toString().toInt())
                setSkill(etSkill.text.toString().toInt())
                setTeam(if(spTeam.selectedItemPosition == 0) getString(R.string.a) else getString(R.string.d))
                if(isEditing && editTransformer != null)
                    setId(editTransformer!!.getId())
            }

            if (!isEditing)
                viewModel.createTransformer(transformer)
            else
                viewModel.editTransformer(transformer)
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
                etName.error = getString(R.string.error_empty_field)
                return false
            }
            etStrength.text.isEmpty() -> {
                etStrength.error = getString(R.string.error_empty_field)
                return false
            }
            !etStrength.text.isDigitsOnly() -> {
                etStrength.error = getString(R.string.error_digits_only)
                return false
            }
            etStrength.text.toString().toInt() !in 0..10 -> {
                etStrength.error = getString(R.string.error_out_range)
                return false
            }
            etIntelligence.text.isEmpty() -> {
                etIntelligence.error = getString(R.string.error_empty_field)
                return false
            }
            !etIntelligence.text.isDigitsOnly() -> {
                etIntelligence.error = getString(R.string.error_digits_only)
                return false
            }
            etIntelligence.text.toString().toInt() !in 0..10 -> {
                etIntelligence.error = getString(R.string.error_out_range)
                return false
            }
            etSpeed.text.isEmpty() -> {
                etSpeed.error = getString(R.string.error_empty_field)
                return false
            }
            !etSpeed.text.isDigitsOnly() -> {
                etSpeed.error = getString(R.string.error_digits_only)
                return false
            }
            etSpeed.text.toString().toInt() !in 0..10 -> {
                etSpeed.error = getString(R.string.error_out_range)
                return false
            }
            etEndurance.text.isEmpty() -> {
                etEndurance.error = getString(R.string.error_empty_field)
                return false
            }
            !etEndurance.text.isDigitsOnly() -> {
                etEndurance.error = getString(R.string.error_digits_only)
                return false
            }
            etEndurance.text.toString().toInt() !in 0..10 -> {
                etEndurance.error = getString(R.string.error_out_range)
                return false
            }
            etRank.text.isEmpty() -> {
                etRank.error = getString(R.string.error_empty_field)
                return false
            }
            !etRank.text.isDigitsOnly() -> {
                etRank.error = getString(R.string.error_digits_only)
                return false
            }
            etRank.text.toString().toInt() !in 0..10 -> {
                etRank.error = getString(R.string.error_out_range)
                return false
            }
            etCourage.text.isEmpty() -> {
                etCourage.error = getString(R.string.error_empty_field)
                return false
            }
            !etCourage.text.isDigitsOnly() -> {
                etCourage.error = getString(R.string.error_digits_only)
                return false
            }
            etCourage.text.toString().toInt() !in 0..10 -> {
                etCourage.error = getString(R.string.error_out_range)
                return false
            }
            etFirepower.text.isEmpty() -> {
                etFirepower.error = getString(R.string.error_empty_field)
                return false
            }
            !etFirepower.text.isDigitsOnly() -> {
                etFirepower.error = getString(R.string.error_digits_only)
                return false
            }
            etFirepower.text.toString().toInt() !in 0..10 -> {
                etFirepower.error = getString(R.string.error_out_range)
                return false
            }
            etSkill.text.isEmpty() -> {
                etSkill.error = getString(R.string.error_empty_field)
                return false
            }
            !etSkill.text.isDigitsOnly() -> {
                etSkill.error = getString(R.string.error_digits_only)
                return false
            }
            etSkill.text.toString().toInt() !in 0..10 -> {
                etSkill.error = getString(R.string.error_out_range)
                return false
            }
            else -> return true
        }
    }

}