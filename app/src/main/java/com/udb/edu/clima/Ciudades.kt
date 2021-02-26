package com.udb.edu.clima

import android.content.Intent
import android.net.http.HttpResponseCache
import android.net.sip.SipSession
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.view.textclassifier.TextLinks
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_ciudades.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class Ciudades : AppCompatActivity() {

    val TAG = "com.udb.edu.clima.ciudades.CIUDADES"

    var listaPersonas:ArrayList<Persona>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)

        val bSoya = findViewById<Button>(R.id.bSoyapango)
        val bSanS = findViewById<Button>(R.id.bSanSalvador)

        bSoya.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(TAG,"ciudad-soyapango")
            startActivity(intent)
        })

        bSanSalvador.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(TAG,"ciudad-sansalvador")
            startActivity(intent)
        })


    }
    //  ./ onCreate
    @Throws(IOException::class)
    private fun getDownloadData(url:String):String     {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var inputStream:InputStream? = null


        try {
            var url = URL(url)
            val conection = url.openConnection() as HttpURLConnection
            conection.requestMethod = "GET"
            conection.connect()

            inputStream = conection.inputStream
            return  inputStream.bufferedReader().use {
                it.readText()
            }
        } finally {
            if (inputStream != null) {
                inputStream.close()
            }
        }
    }
    // ./ getDownloadData


}