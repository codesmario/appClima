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
        var validar = findViewById<Button>(R.id.btnValidarCon)
        var tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        var tbVolley = findViewById<Button>(R.id.bVolley)

        var btnRequest =  findViewById<Button>(R.id.btnRequest)

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

        btnRequest.setOnClickListener {
            if (Network.hayRed(this)){
                Log.d("bSolicitudOnClic", getDownloadData("www.facebook.com"))
            }
        }

        //
        tbVolley.setOnClickListener {
            if (Network.hayRed(this)) {
                solicitudHTTPVolley("https://reqres.in/api/users?page=2")
            } else {
                tvWelcome.text = "No hay una conexion a internet"
                Toast.makeText(this, "No hay una conexion a internet", Toast.LENGTH_SHORT).show()
            }
        }

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

    private fun solicitudHTTPVolley(url:String) {
        val queue = Volley.newRequestQueue(this)
        val solicitud = StringRequest(Request.Method.GET , url, {
            response ->
            try {
                Log.d("solicitudHTTPVolley", response)

                val json = JSONObject(response)
                val datos = json.getJSONArray ("data")
                listaPersonas = ArrayList()
                for ( i in 0..datos.length() - 1 ) {
                    val fname = datos.getJSONObject(i).getString("first_name")
                    val lname = datos.getJSONObject(i).getString("last_name")
                    val email = datos.getJSONObject(i).getString("email")
                    val avatar = datos.getJSONObject(i).getString("avatar")

                    listaPersonas?.add(Persona(fname,lname,email,avatar))

                }


            } catch ( e: Exception) {

            }
        }, {  })
        queue.add(solicitud)
    }
}