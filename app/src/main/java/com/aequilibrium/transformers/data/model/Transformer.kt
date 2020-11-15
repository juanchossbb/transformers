package com.aequilibrium.transformers.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Transformer : Serializable{
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
    private var teamIcon : String? = null

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

  public fun setId(id : String){
      this.id = id
  }

    public fun getName() : String{
        return name
    }

    public fun getTeamIcon() : String?{
        return teamIcon
    }

    public fun getOverall() : Int{
        return strength + intelligence + speed + endurance +firepower
    }

    public fun getId() : String{
        return id
    }

    public fun getStrength() : String{
        return strength.toString()
    }

    public fun getIntelligence() : String{
        return intelligence.toString()
    }

    public fun getSpeed() : String{
        return speed.toString()
    }

    public fun getEndurance() : String{
        return endurance.toString()
    }

    public fun getRank() : String{
        return rank.toString()
    }

    public fun getCourage() : String{
        return courage.toString()
    }

    public fun getFirepower() : String{
        return firepower.toString()
    }

    public fun getSkill() : String{
        return skill.toString()
    }

    public fun getTeam() : String{
        return team
    }
}