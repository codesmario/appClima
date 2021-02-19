package com.udb.edu.clima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var tvCiudad: TextView? = null
    var tvGrados: TextView? = null
    var tvEstatus: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvGrados = findViewById(R.id.tvGrados)
        tvCiudad = findViewById(R.id.tvCiudad)
        tvEstatus = findViewById(R.id.tvEstado)

        val ciudad = intent.getStringExtra("com.udb.edu.clima.ciudades.CIUDADES")



        val ciudadSy = Ciudad("Ciudad de Soyapango",18,"Soleado")

        if (ciudad == "ciudad-soyapango") {

            // mostrar info de soyapango city
            tvGrados?.text = ciudadSy.grados.toString()
            tvCiudad?.text = ciudadSy.nombre
            tvEstatus?.text = ciudadSy.estatus

        } else {

            Toast.makeText(this,"Ciudad no encontrada",Toast.LENGTH_SHORT).show()

        }

    }


}