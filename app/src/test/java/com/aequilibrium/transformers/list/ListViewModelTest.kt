package com.aequilibrium.transformers.list

import com.aequilibrium.transformers.BaseTest
import com.aequilibrium.transformers.data.model.Transformer
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test

class ListViewModelTest : BaseTest(){
    lateinit var viewModel: ListViewModel

    @Before
    fun setUp(){
        viewModel = ListViewModel()
    }

    @Test
    fun getLoserByCourageTest(){
        val transformer1 = Transformer().apply {setCourage(1)}

        val transformer2 = Transformer().apply { setCourage(5) }

        val transformer3 = Transformer().apply { setCourage(2) }

        assertEquals("Difference in courage >= 4, transformer1 should lose",transformer1,viewModel.getLoserByCourage(transformer1,transformer2))
        assertNull("Difference in courage not big enough to declare winner", viewModel.getLoserByCourage(transformer1,transformer3))
    }

    @Test
    fun getLoserByStrenghtTest(){
        val transformer1 = Transformer().apply {setStrength(1)}

        val transformer2 = Transformer().apply { setStrength(4) }

        val transformer3 = Transformer().apply { setStrength(2) }

        assertEquals("Difference in strength >= 3, transformer1 should lose",transformer1,viewModel.getLoserByStrength(transformer1,transformer2))
        assertNull("Difference in strength not big enough to declare winner", viewModel.getLoserByStrength(transformer1,transformer3))
    }

    @Test
    fun getLoserByOverallTest(){
        val transformer1 = Transformer().apply {
            setStrength(1)
            setCourage(1)
            setIntelligence(1)
            setSpeed(1)
            setEndurance(1)
            setRank(1)
            setCourage(1)
            setFirepower(1)
            setSkill(1)
        }

        val transformer2 = Transformer().apply {
            setStrength(1)
            setCourage(1)
            setIntelligence(1)
            setSpeed(1)
            setEndurance(2)
            setRank(2)
            setCourage(2)
            setFirepower(2)
            setSkill(2)
        }

        val transformer3 = Transformer().apply {
            setStrength(1)
            setCourage(1)
            setIntelligence(1)
            setSpeed(1)
            setEndurance(1)
            setRank(1)
            setCourage(1)
            setFirepower(1)
            setSkill(1)
        }

        assertTrue("Overall score should be higher for Transformer2", transformer1.getOverall() < transformer2.getOverall())
        assertEquals("Loser should be Transformer 1", transformer1, viewModel.getLoserByOverall(transformer1,transformer2))
        assertEquals("Overall score should be the same",transformer1.getOverall(),transformer3.getOverall())
        assertNull("No winner if overall score is the same", viewModel.getLoserByOverall(transformer1,transformer3))
    }

    @Test
    fun getLoserBySpecialRuleTest(){
        val transformer1 = Transformer().apply { setName("Optimus Prime") }
        val transformer2 = Transformer().apply { setName("Predaking") }
        val transformer3 = Transformer().apply { setName("Bumblebee") }
        val transformer4 = Transformer().apply { setName("other transformer") }

        assertEquals("Bumblebee should always lose against optimus prime",transformer3, viewModel.getLoserBySpecialRule(transformer1,transformer3))
        assertEquals("Bumblebee should always lose against predaking",transformer3, viewModel.getLoserBySpecialRule(transformer2,transformer3))
        assertEquals("Optimus prime vs Predaking means total destruction",viewModel.totalDestructionEvent, viewModel.getLoserBySpecialRule(transformer1,transformer2))
        assertNull("If neither transformer is Optimus or Predaking, no winner by special rule",viewModel.getLoserBySpecialRule(transformer3,transformer4
        ))
    }

}