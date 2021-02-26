package com.udb.edu.clima

class Ciudad (name:String, weather:ArrayList<Wheater>, main:Main){
    var name:String = ""
    var weather:ArrayList<Wheater>? = null
    var main:Main? = null

    init {
        this.name = name
        this.weather = weather
        this.main = main
    }

}