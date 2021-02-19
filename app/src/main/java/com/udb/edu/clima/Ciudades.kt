package com.udb.edu.clima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Ciudades : AppCompatActivity() {

    val TAG = "com.udb.edu.clima.ciudades.CIUDADES"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)

        val bSoya = findViewById<Button>(R.id.bSoyapango)
        var validar = findViewById<Button>(R.id.btnValidarCon)
        var tvWelcome = findViewById<TextView>(R.id.tvWelcome)

        validar.setOnClickListener {
            Toast.makeText(this,"En listener",Toast.LENGTH_SHORT)

            if (Network.hayRed(this)) {
                tvWelcome.text = "Hay conexión"
            } else {
                tvWelcome.text = "No hay conexión"
            }
        }

        bSoya.setOnClickListener(View.OnClickListener {
            // Toast.makeText(this,"Ciudad de Soyapango",Toast.LENGTH_SHORT)

            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(TAG,"ciudad-soyapango")
            startActivity(intent)


        })



    }
}