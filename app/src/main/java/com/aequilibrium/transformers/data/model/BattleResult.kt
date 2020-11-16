package com.aequilibrium.transformers.data.model

import java.io.Serializable

class BattleResult : Serializable{
    private  var winner : String? = null
    private lateinit var destroyedTransformers : MutableList<Transformer>

    public fun setWinner(winner : String?){
        this.winner = winner
    }

    public fun setDestroyedTransformers(list : MutableList<Transformer>){
        this.destroyedTransformers = list
    }

    public fun getWinner() : String?{
        return winner
    }

    public fun getDestroyedTransformers() : MutableList<Transformer>{
        return destroyedTransformers
    }
}