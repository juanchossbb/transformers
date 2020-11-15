package com.aequilibrium.transformers.createedit

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import com.aequilibrium.transformers.BaseTest
import com.aequilibrium.transformers.data.model.Transformer
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test

class CreateEditFragmentTest : BaseTest(){

    lateinit var fragment: CreateEditFragment

    @Before
    fun setUp(){
        with(launchFragmentInContainer<CreateEditFragment>().recreate())    {
            onFragment { fragment->
                this@CreateEditFragmentTest.fragment = fragment
            }
        }
    }

    @Test
    fun fragmentForTransformerCreationTest(){
        fragment.onViewCreated(fragment.view!!,null)
        assertFalse("fragment should not be created for editing",fragment.isEditing)
        val transformer = fragment.arguments?.get(Transformer::class.java.canonicalName)
        assertNull("Fragment should have not received a Transformer object", transformer)
    }

    @Test
    fun fragmentForTransformerEditingTest(){
        fragment.arguments = bundleOf(Transformer::class.java.canonicalName to Transformer().apply {
            setName("transformer")
            setTeam("A")
        })
        fragment.onViewCreated(fragment.view!!,null)
        assertTrue("fragment should be created for editing",fragment.isEditing)
        val transformer = fragment.arguments?.get(Transformer::class.java.canonicalName)
        assertNotNull("Fragment should have received a Transformer object", transformer)
    }

    @Test
    fun fillFieldsForEditingTest(){
        assertEquals("fragment title should be set for creating","Create Transformer",fragment.tvTitle.text.toString())
        assertTrue("name field should be empty",fragment.etName.text.isEmpty())
        assertTrue("strength field should be empty",fragment.etStrength.text.isEmpty())
        assertTrue("intelligence field should be empty",fragment.etIntelligence.text.isEmpty())
        assertTrue("speed field should be empty",fragment.etSpeed.text.isEmpty())
        assertTrue("endurance field should be empty",fragment.etEndurance.text.isEmpty())
        assertTrue("rank field should be empty",fragment.etRank.text.isEmpty())
        assertTrue("courage field should be empty",fragment.etCourage.text.isEmpty())
        assertTrue("firepower field should be empty",fragment.etFirepower.text.isEmpty())
        assertTrue("skill field should be empty",fragment.etSkill.text.isEmpty())
        assertEquals("team spinner should be on default value","Autobots",fragment.spTeam.selectedItem)
        assertNull("Edit transformer should be null",fragment.editTransformer)
        assertEquals("button text should be set for creating","Submit", fragment.btnSubmit.text.toString())
        assertFalse("isEditing value should be false", fragment.isEditing)


        val transformer = Transformer().apply {
            setName("transformer")
            setStrength(1)
            setIntelligence(2)
            setSpeed(3)
            setEndurance(4)
            setRank(5)
            setCourage(6)
            setFirepower(7)
            setSkill(8)
            setTeam("D")
        }
        fragment.fillFieldsForEditing(transformer)

        assertEquals("fragment title should be set for editing","Edit Transformer",fragment.tvTitle.text.toString())
        assertTrue("name field should not be empty",fragment.etName.text.isNotEmpty())
        assertTrue("strength field should not be empty",fragment.etStrength.text.isNotEmpty())
        assertTrue("intelligence field should not be empty",fragment.etIntelligence.text.isNotEmpty())
        assertTrue("speed field should not be empty",fragment.etSpeed.text.isNotEmpty())
        assertTrue("endurance field should not be empty",fragment.etEndurance.text.isNotEmpty())
        assertTrue("rank field should not be empty",fragment.etRank.text.isNotEmpty())
        assertTrue("courage field should not be empty",fragment.etCourage.text.isNotEmpty())
        assertTrue("firepower field should not be empty",fragment.etFirepower.text.isNotEmpty())
        assertTrue("skill field should not be empty",fragment.etSkill.text.isNotEmpty())
        assertEquals("team spinner should not be on default value","Decepticons",fragment.spTeam.selectedItem)
        assertNotNull("Edit transformer should not be null",fragment.editTransformer)
        assertEquals("button text should be set for editing","Edit Transformer", fragment.btnSubmit.text.toString())
        assertTrue("isEditing value should not be false", fragment.isEditing)


    }

    @Test
    fun checkViewModelTest(){
        assertNotNull("View model should not be null",fragment.viewModel)
        assertEquals("ViewModel should be of type CreateEditViewModel", CreateEditViewModel::class.java, fragment.viewModel::class.java)
    }

    @Test
    fun fragmentsViewsTest(){
        assertNotNull("view should not be null", fragment.tvTitle)
        assertNotNull("view should not be null", fragment.etName)
        assertNotNull("view should not be null", fragment.etStrength)
        assertNotNull("view should not be null", fragment.etIntelligence)
        assertNotNull("view should not be null", fragment.etSpeed)
        assertNotNull("view should not be null", fragment.etEndurance)
        assertNotNull("view should not be null", fragment.etRank)
        assertNotNull("view should not be null", fragment.etCourage)
        assertNotNull("view should not be null", fragment.etFirepower)
        assertNotNull("view should not be null", fragment.etSkill)
        assertNotNull("view should not be null", fragment.spTeam)
        assertNotNull("view should not be null", fragment.btnSubmit)
    }

    @Test
    fun validateFieldsTest(){
        assertFalse("Validate fields should return false as fields are empty",fragment.validateFields())
        assertEquals("view should show empty field error","This field should not be empty",fragment.etName.error.toString())
        fragment.etName.setText("Transformes name")
        fragment.validateFields()
        assertTrue(fragment.etName.error.isNullOrEmpty())
        assertEquals("view should show empty field error","This field should not be empty",fragment.etStrength.error.toString())

        assertFalse("Validate fields should return false as fields are empty",fragment.validateFields())
        assertEquals("view should show empty field error","This field should not be empty",fragment.etStrength.error.toString())
        fragment.etStrength.setText("strength")
        fragment.validateFields()
        assertEquals("view should only allow numbers","This field should only contain numbers",fragment.etStrength.error.toString())
        fragment.etStrength.setText("11")
        fragment.validateFields()
        assertEquals("value out of range","Value should be between 0 and 10",fragment.etStrength.error.toString())
        fragment.etStrength.setText("1")
        fragment.validateFields()
        assertTrue(fragment.etStrength.error.isNullOrEmpty())

        assertFalse("Validate fields should return false as fields are empty",fragment.validateFields())
        assertEquals("view should show empty field error","This field should not be empty",fragment.etIntelligence.error.toString())
        fragment.etIntelligence.setText("intelligence")
        fragment.validateFields()
        assertEquals("view should only allow numbers","This field should only contain numbers",fragment.etIntelligence.error.toString())
        fragment.etIntelligence.setText("12")
        fragment.validateFields()
        assertEquals("value out of range","Value should be between 0 and 10",fragment.etIntelligence.error.toString())
        fragment.etIntelligence.setText("2")
        fragment.validateFields()
        assertTrue(fragment.etIntelligence.error.isNullOrEmpty())

        assertFalse("Validate fields should return false as fields are empty",fragment.validateFields())
        assertEquals("view should show empty field error","This field should not be empty",fragment.etSpeed.error.toString())
        fragment.etSpeed.setText("speed")
        fragment.validateFields()
        assertEquals("view should only allow numbers","This field should only contain numbers",fragment.etSpeed.error.toString())
        fragment.etSpeed.setText("13")
        fragment.validateFields()
        assertEquals("value out of range","Value should be between 0 and 10",fragment.etSpeed.error.toString())
        fragment.etSpeed.setText("3")
        fragment.validateFields()
        assertTrue(fragment.etSpeed.error.isNullOrEmpty())

        assertFalse("Validate fields should return false as fields are empty",fragment.validateFields())
        assertEquals("view should show empty field error","This field should not be empty",fragment.etEndurance.error.toString())
        fragment.etEndurance.setText("endurance")
        fragment.validateFields()
        assertEquals("view should only allow numbers","This field should only contain numbers",fragment.etEndurance.error.toString())
        fragment.etEndurance.setText("14")
        fragment.validateFields()
        assertEquals("value out of range","Value should be between 0 and 10",fragment.etEndurance.error.toString())
        fragment.etEndurance.setText("4")
        fragment.validateFields()
        assertTrue(fragment.etEndurance.error.isNullOrEmpty())

        assertFalse("Validate fields should return false as fields are empty",fragment.validateFields())
        assertEquals("view should show empty field error","This field should not be empty",fragment.etRank.error.toString())
        fragment.etRank.setText("rank")
        fragment.validateFields()
        assertEquals("view should only allow numbers","This field should only contain numbers",fragment.etRank.error.toString())
        fragment.etRank.setText("15")
        fragment.validateFields()
        assertEquals("value out of range","Value should be between 0 and 10",fragment.etRank.error.toString())
        fragment.etRank.setText("5")
        fragment.validateFields()
        assertTrue(fragment.etRank.error.isNullOrEmpty())

        assertFalse("Validate fields should return false as fields are empty",fragment.validateFields())
        assertEquals("view should show empty field error","This field should not be empty",fragment.etCourage.error.toString())
        fragment.etCourage.setText("courage")
        fragment.validateFields()
        assertEquals("view should only allow numbers","This field should only contain numbers",fragment.etCourage.error.toString())
        fragment.etCourage.setText("11")
        fragment.validateFields()
        assertEquals("value out of range","Value should be between 0 and 10",fragment.etCourage.error.toString())
        fragment.etCourage.setText("6")
        fragment.validateFields()
        assertTrue(fragment.etCourage.error.isNullOrEmpty())

        assertFalse("Validate fields should return false as fields are empty",fragment.validateFields())
        assertEquals("view should show empty field error","This field should not be empty",fragment.etFirepower.error.toString())
        fragment.etFirepower.setText("firepower")
        fragment.validateFields()
        assertEquals("view should only allow numbers","This field should only contain numbers",fragment.etFirepower.error.toString())
        fragment.etFirepower.setText("17")
        fragment.validateFields()
        assertEquals("value out of range","Value should be between 0 and 10",fragment.etFirepower.error.toString())
        fragment.etFirepower.setText("7")
        fragment.validateFields()
        assertTrue(fragment.etFirepower.error.isNullOrEmpty())

        assertFalse("Validate fields should return false as fields are empty",fragment.validateFields())
        assertEquals("view should show empty field error","This field should not be empty",fragment.etSkill.error.toString())
        fragment.etSkill.setText("skill")
        fragment.validateFields()
        assertEquals("view should only allow numbers","This field should only contain numbers",fragment.etSkill.error.toString())
        fragment.etSkill.setText("18")
        fragment.validateFields()
        assertEquals("value out of range","Value should be between 0 and 10",fragment.etSkill.error.toString())
        fragment.etSkill.setText("8")
        fragment.validateFields()
        assertTrue(fragment.etSkill.error.isNullOrEmpty())

        assertTrue("Validate should return true as all fields are filled properly", fragment.validateFields())
    }
}