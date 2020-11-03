package com.example.tarea2davidjson

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    val REQUEST_CODE_REGISTRO = 10
    var usuario = Users()
    lateinit var buttonLog: Button
    lateinit var buttonInf: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonLog = findViewById(R.id.buttonLogin)
        buttonInf = findViewById(R.id.buttonInfo)
        buttonInf.isEnabled = false
    }

    fun onLogin(view: View) {
        var loginDialog = LoginDialogs()
        var nombrefichero = "mifichero.json"
        var bufferedReader = BufferedReader(InputStreamReader(openFileInput(nombrefichero)))
        var textoLeido = bufferedReader.readLine()
        val jsonjObject = JSONObject(textoLeido)
        loginDialog.users = jsonjObject.getString("usuario")
        loginDialog.password = jsonjObject.getString("password")
        bufferedReader.close()
        loginDialog.mainActivity = this
        loginDialog.show(supportFragmentManager, "Dialog_Tag")
    }

    fun onRegistro(view: View) {
        var miIntent = Intent(this, Registro::class.java)
        miIntent.putExtra("registro", true)
        startActivityForResult(miIntent, REQUEST_CODE_REGISTRO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_REGISTRO) {
                var bundleData = data!!.getBundleExtra("usuario")
                usuario.setBundle(bundleData!!)
                buttonLog.isEnabled = true
            }
        }
    }

    fun onInformacion(view: View) {
        var miIntent = Intent(this, Registro::class.java)
        miIntent.putExtra("registro", false)
        startActivity(miIntent)
    }

    fun onActivarInformacion() {
        buttonInf.isEnabled = true
    }
}