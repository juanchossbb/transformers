package com.aequilibrium.transformers.data.model

import com.google.gson.annotations.SerializedName

class Transformer {
    private lateinit var id : String
    private lateinit var name : String
    private var strength : Int = 0
    private var intelligence : Int = 0
    private var speed : Int = 0
    private var endurance : Int = 0
    private var rank : Int = 0
    private var courage : Int = 0
    private var firepower : Int = 0
    private var skill : Int = 0
    private lateinit var team : String
    @SerializedName("team_icon")
    private lateinit var teamIcon : String

    public fun setName(name : String){
        this.name = name
    }

    public fun setStrength(strength : Int){
        this.strength = strength
    }

    public fun setIntelligence(intelligence : Int){
        this.intelligence = intelligence
    }

    public fun setSpeed(speed : Int){
        this.speed = speed
    }

    public fun setEndurance(endurance : Int){
        this.endurance = endurance
    }

    public fun setRank(rank : Int){
        this.rank = rank
    }

    public fun setCourage(courage : Int){
        this.courage = courage
    }

    public fun setFirepower(firepower : Int){
        this.firepower = firepower
    }

    public fun setSkill(skill : Int){
        this.skill = skill
    }

    public fun setTeam(team : String){
        this.team = team
    }

    public fun getName() : String{
        return name
    }
}