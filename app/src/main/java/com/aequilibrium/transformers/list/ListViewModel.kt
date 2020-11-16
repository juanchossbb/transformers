package com.aequilibrium.transformers.list

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aequilibrium.transformers.data.Repository
import com.aequilibrium.transformers.data.model.BattleResult
import com.aequilibrium.transformers.data.model.Transformer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel(){
    val repository = Repository()
    val livedata = MutableLiveData<MutableList<Transformer>>()
    val removeLiveData = MutableLiveData<Int>()
    val errorLiveData = MutableLiveData<String>()
    val battleLiveData = MutableLiveData<BattleResult>()
    val totalDestructionEvent = Transformer().apply {
        setName("total destruction")
    }

    fun getTransformerList(){
        CoroutineScope(Dispatchers.IO).launch {
            repository.retrieveTransformerList().subscribe { response, error ->
                if (response!=null){
                    livedata.postValue(response.transformers)
                }else if(!error.message.isNullOrEmpty()){
                    errorLiveData.postValue(error.message)
                }
            }
        }
    }

    fun removeTransformer(transformer: Transformer){
        CoroutineScope(Dispatchers.IO).launch {
            repository.removeTransformer(transformer).subscribe { response, error ->
                if (response!=null){
                    removeLiveData.postValue(204)
                }else if (error != null){
                    removeLiveData.postValue(401)
                }
            }
        }
    }

    fun wageWar(list : MutableList<Transformer>){
        CoroutineScope(Dispatchers.IO).launch {
            val decepticonList = list.filter { it.getTeam() == "D" }.sortedBy { it.getRank() }.reversed()
            val autobotList = list.filter { it.getTeam() == "A" }.sortedBy { it.getRank() }.reversed()
            val numberOfBattles = if(decepticonList.size > autobotList.size) autobotList.size else decepticonList.size
            var destroyedTransformers = arrayListOf<Transformer>()
            for (i in 0 until numberOfBattles){
                val decepticon = decepticonList[i]
                val autobot = autobotList[i]

                val loserBySpecialRule = getLoserBySpecialRule(decepticon,autobot)
                if (loserBySpecialRule == autobot){
                    destroyedTransformers.addAll(autobotList.subList(0,numberOfBattles))
                    break
                }else if(loserBySpecialRule == decepticon){
                    destroyedTransformers.addAll(decepticonList.subList(0,numberOfBattles))
                    break
                }else if (loserBySpecialRule == totalDestructionEvent){
                    destroyedTransformers.addAll(autobotList)
                    destroyedTransformers.addAll(decepticonList)
                    break
                }else{
                    getLoserByCourage(decepticon,autobot)?.let {
                        destroyedTransformers.add(it)
                    }?: getLoserByStrength(decepticon,autobot)?.let {
                        destroyedTransformers.add(it)
                    }?: getLoserByOverall(decepticon,autobot)?.let {
                        destroyedTransformers.add(it)
                    }?:destroyedTransformers.add(autobot).also { destroyedTransformers.add(decepticon)}

                }

            }
            battleLiveData.postValue(getBattleResult(destroyedTransformers))
        }
    }

    private fun getBattleResult(destroyedTransformers : MutableList<Transformer>) : BattleResult{
        val destroyedAutobots = destroyedTransformers.filter { it.getTeam() == "A" }.size
        val destroyedDecepticons = destroyedTransformers.filter { it.getTeam() == "D" }.size
        val winner = when {
            destroyedAutobots > destroyedDecepticons -> "Autobots"
            destroyedAutobots < destroyedDecepticons -> "Decepticons"
            else -> null
        }
        return BattleResult().apply {
            setWinner(winner)
            setDestroyedTransformers(destroyedTransformers)
        }
    }

    @VisibleForTesting
    fun getLoserByCourage(decepticon : Transformer, autobot : Transformer) : Transformer?{
        val courageDiff = decepticon.getCourage().toInt() - autobot.getCourage().toInt()
        return when {
            courageDiff >= 4 ->  autobot
            courageDiff <= -4 ->  decepticon
            else -> null
        }
    }

    @VisibleForTesting
    fun getLoserByStrength(decepticon : Transformer, autobot : Transformer) : Transformer?{
        val strenghtDiff = decepticon.getStrength().toInt() - autobot.getStrength().toInt()
        return when {
            strenghtDiff >= 3 ->  autobot
            strenghtDiff <= -3 ->  decepticon
            else -> null
        }
    }

    @VisibleForTesting
    fun getLoserByOverall(decepticon : Transformer, autobot : Transformer) : Transformer?{
        return when {
            decepticon.getOverall() > autobot.getOverall() -> autobot
            decepticon.getOverall() < autobot.getOverall() -> decepticon
            else -> null
        }
    }

    @VisibleForTesting
    fun getLoserBySpecialRule(decepticon : Transformer, autobot : Transformer) : Transformer?{
        val names = arrayOf("optimus prime","predaking")
        if (decepticon.getName().toLowerCase() in names){
            return if (autobot.getName().toLowerCase() !in names){
                autobot
            }else{
                totalDestructionEvent
            }
        }else if (autobot.getName().toLowerCase() in names){
            return decepticon
        }

        return null
    }
}