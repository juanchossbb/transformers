package com.aequilibrium.transformers.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aequilibrium.transformers.MainActivityInterface
import com.aequilibrium.transformers.R
import com.aequilibrium.transformers.data.model.BattleResult

class BattleResultFragment : Fragment(){
    lateinit var rvDestroyedDecepticons : RecyclerView
    lateinit var rvDestroyedAutobots : RecyclerView
    lateinit var tvWinner : TextView
    lateinit var mainInterface : MainActivityInterface

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_battle_result,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvDestroyedDecepticons = view.findViewById(R.id.rv_battle_decepticons)
        rvDestroyedAutobots = view.findViewById(R.id.rv_battle_autobots)
        tvWinner = view.findViewById(R.id.tv_battle_winner_value)

        val battleResult = arguments?.get(BattleResult::class.java.canonicalName) as BattleResult?
        battleResult?.let {
            paintBattleInfo(it)
        }
    }

    @VisibleForTesting
    fun paintBattleInfo(result : BattleResult){
        result.getWinner()?.let {
            tvWinner.text = it
        }?: tvWinner.setText(R.string.battle_tie)
        val layoutManagerDecepticons = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        val decepticonsAdapter = BattleResultAdapter(result.getDestroyedTransformers().filter { it.getTeam() == getString(R.string.d) })
        rvDestroyedDecepticons.adapter = decepticonsAdapter
        rvDestroyedDecepticons.layoutManager = layoutManagerDecepticons

        val layoutManagerAutobots = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        val autobotsAdapter = BattleResultAdapter(result.getDestroyedTransformers().filter { it.getTeam() == getString(R.string.a) })
        rvDestroyedAutobots.adapter = autobotsAdapter
        rvDestroyedAutobots.layoutManager = layoutManagerAutobots
    }


    companion object{
        fun getBattleResultFragment(result : BattleResult) : BattleResultFragment{
            return BattleResultFragment().apply { arguments = bundleOf(BattleResult::class.java.canonicalName to result) }
        }
    }
}