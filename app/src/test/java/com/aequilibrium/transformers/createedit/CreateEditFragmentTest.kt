package com.aequilibrium.transformers.createedit

import androidx.fragment.app.testing.launchFragmentInContainer
import com.aequilibrium.transformers.BaseTest
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
        fragment.etStrength.setText("1")
        fragment.validateFields()
        assertTrue(fragment.etStrength.error.isNullOrEmpty())

        assertFalse("Validate fields should return false as fields are empty",fragment.validateFields())
        assertEquals("view should show empty field error","This field should not be empty",fragment.etIntelligence.error.toString())
        fragment.etIntelligence.setText("intelligence")
        fragment.validateFields()
        assertEquals("view should only allow numbers","This field should only contain numbers",fragment.etIntelligence.error.toString())
        fragment.etIntelligence.setText("2")
        fragment.validateFields()
        assertTrue(fragment.etIntelligence.error.isNullOrEmpty())

        assertFalse("Validate fields should return false as fields are empty",fragment.validateFields())
        assertEquals("view should show empty field error","This field should not be empty",fragment.etSpeed.error.toString())
        fragment.etSpeed.setText("speed")
        fragment.validateFields()
        assertEquals("view should only allow numbers","This field should only contain numbers",fragment.etSpeed.error.toString())
        fragment.etSpeed.setText("3")
        fragment.validateFields()
        assertTrue(fragment.etSpeed.error.isNullOrEmpty())

        assertFalse("Validate fields should return false as fields are empty",fragment.validateFields())
        assertEquals("view should show empty field error","This field should not be empty",fragment.etEndurance.error.toString())
        fragment.etEndurance.setText("endurance")
        fragment.validateFields()
        assertEquals("view should only allow numbers","This field should only contain numbers",fragment.etEndurance.error.toString())
        fragment.etEndurance.setText("4")
        fragment.validateFields()
        assertTrue(fragment.etEndurance.error.isNullOrEmpty())

        assertFalse("Validate fields should return false as fields are empty",fragment.validateFields())
        assertEquals("view should show empty field error","This field should not be empty",fragment.etRank.error.toString())
        fragment.etRank.setText("rank")
        fragment.validateFields()
        assertEquals("view should only allow numbers","This field should only contain numbers",fragment.etRank.error.toString())
        fragment.etRank.setText("5")
        fragment.validateFields()
        assertTrue(fragment.etRank.error.isNullOrEmpty())

        assertFalse("Validate fields should return false as fields are empty",fragment.validateFields())
        assertEquals("view should show empty field error","This field should not be empty",fragment.etCourage.error.toString())
        fragment.etCourage.setText("courage")
        fragment.validateFields()
        assertEquals("view should only allow numbers","This field should only contain numbers",fragment.etCourage.error.toString())
        fragment.etCourage.setText("6")
        fragment.validateFields()
        assertTrue(fragment.etCourage.error.isNullOrEmpty())

        assertFalse("Validate fields should return false as fields are empty",fragment.validateFields())
        assertEquals("view should show empty field error","This field should not be empty",fragment.etFirepower.error.toString())
        fragment.etFirepower.setText("firepower")
        fragment.validateFields()
        assertEquals("view should only allow numbers","This field should only contain numbers",fragment.etFirepower.error.toString())
        fragment.etFirepower.setText("7")
        fragment.validateFields()
        assertTrue(fragment.etFirepower.error.isNullOrEmpty())

        assertFalse("Validate fields should return false as fields are empty",fragment.validateFields())
        assertEquals("view should show empty field error","This field should not be empty",fragment.etSkill.error.toString())
        fragment.etSkill.setText("skill")
        fragment.validateFields()
        assertEquals("view should only allow numbers","This field should only contain numbers",fragment.etSkill.error.toString())
        fragment.etSkill.setText("8")
        fragment.validateFields()
        assertTrue(fragment.etSkill.error.isNullOrEmpty())

        assertTrue("Validate should return true as all fields are fielled properly", fragment.validateFields())
    }
}