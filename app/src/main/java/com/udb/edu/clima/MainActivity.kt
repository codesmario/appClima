package com.udb.edu.clima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var tvCiudad: TextView? = null
    var tvGrados: TextView? = null
    var tvEstatus: TextView? = null
    val APIKEY:String = "2bc3dc743b46d05ab15e31ebf1eae687"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var ciudad = intent.getStringExtra("com.udb.edu.clima.ciudades.CIUDADES")

        tvGrados = findViewById(R.id.tvGrados)
        tvCiudad = findViewById(R.id.tvCiudad)
        tvEstatus = findViewById(R.id.tvEstado)


        if (Network.hayRed(this)) {

            if (ciudad=="ciudad-sansalvador") {
                solicitudHTTPVolley("http://api.openweathermap.org/data/2.5/weather?q=San%20Salvador&appid="+APIKEY+"&units=metric&lang=es")
            } else {
                solicitudHTTPVolley("http://api.openweathermap.org/data/2.5/weather?q=Soyapango&appid="+APIKEY+"&units=metric&lang=es")
            }


            tvGrados?.text = "-t"
            tvCiudad?.text = "-c"
            tvEstatus?.text = "-s"

        } else {
            Toast.makeText(this,"No hay conexión a internet",Toast.LENGTH_SHORT)
        }

    }





    private fun solicitudHTTPVolley(url:String) {
        val queue = Volley.newRequestQueue(this)
        val solicitud = StringRequest(Request.Method.GET , url, {
                response ->
            try {

                val gson = Gson()
                val ciudad = gson.fromJson(response,Ciudad::class.java)
                tvCiudad?.text = ciudad.name
                tvGrados?.text = ciudad.main?.temp.toString()+" C°"
                tvEstatus?.text = ciudad.weather?.get(0)?.description

            } catch ( e: Exception) {

            }
        }, {  })
        queue.add(solicitud)
    }
}