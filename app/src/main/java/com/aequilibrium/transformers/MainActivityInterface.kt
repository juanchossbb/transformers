package com.aequilibrium.transformers

import com.aequilibrium.transformers.data.model.BattleResult
import com.aequilibrium.transformers.data.model.Transformer

interface MainActivityInterface {
    fun launchCreateFragment()
    fun launchTransformerListFragment()
    fun launchEditFragment(transformer: Transformer)
    fun showProgressBar(show : Boolean)
    fun launchErrorAlertDialog(message : String)
    fun launchBattleResultFragment(result : BattleResult)
}