package com.example.tarea2davidjson

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.*
import android.os.Bundle
import android.view.View

class Registro : AppCompatActivity() {
    lateinit var et_usr: EditText
    lateinit var et_pwd: EditText
    lateinit var et_nom: EditText
    lateinit var et_ape: EditText
    lateinit var users: String
    lateinit var passwords: String
    lateinit var nombres: String
    lateinit var apellidos: String
    lateinit var buttonActualiza: Button
    var registro = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        et_usr = findViewById(R.id.editTextTextUsr)
        et_pwd = findViewById(R.id.editTextTextPwd)
        et_nom = findViewById(R.id.editTextTextNombre)
        et_ape = findViewById(R.id.editTextPwd)
        buttonActualiza = findViewById(R.id.buttonOk)
        var registro = intent.getBooleanExtra("registro", true)
        if (!registro) {
            buttonActualiza.text = "Actualizar"
            var nombrefichero = "mifichero.json"
            var bufferedReader = BufferedReader(InputStreamReader(openFileInput(nombrefichero)))
            var textoLeido = bufferedReader.readLine()
            var jsonjObject = JSONObject(textoLeido)
            et_usr.setText(jsonjObject.getString("usuario"))
            et_pwd.setText(jsonjObject.getString("password"))
            et_nom.setText(jsonjObject.getString("nombre"))
            et_ape.setText(jsonjObject.getString("apellidos"))
            bufferedReader.close()
        }
    }

    fun onAceptar(view: View) {
        var nombrefichero = "mifichero.json"
        if (registro) {
            var usuario = Users()
            users = et_usr.text.toString()
            passwords = et_pwd.text.toString()
            nombres = et_nom.text.toString()
            apellidos = et_ape.text.toString()
            usuario.usuario = users
            usuario.password = passwords
            usuario.nombre = nombres
            usuario.apellido = apellidos
            var fileOutput = openFileOutput(nombrefichero, Context.MODE_PRIVATE)
            var outputStreamWriter = OutputStreamWriter(fileOutput)
            val onInput = JSONObject()
            onInput.put("usuario", users)
            onInput.put("password", passwords)
            onInput.put("nombre", nombres)
            onInput.put("apellidos", apellidos)
            outputStreamWriter.write(onInput.toString())
            outputStreamWriter.close()
            fileOutput.close()
            var resultIntent = Intent(this, MainActivity::class.java)
            resultIntent.putExtra("usuario", usuario.getBundle())
            setResult(Activity.RESULT_OK, resultIntent)
        } else {
            var bufferedReader = BufferedReader(InputStreamReader(openFileInput(nombrefichero)))
            var textoLeido = bufferedReader.readLine()
            var jsonjObject = JSONObject(textoLeido)
            bufferedReader.close()
            var fileOutput = openFileOutput(nombrefichero, Context.MODE_PRIVATE)
            var outputStreamWriter = OutputStreamWriter(fileOutput)
            users = et_usr.text.toString()
            passwords = et_pwd.text.toString()
            nombres = et_nom.text.toString()
            apellidos = et_ape.text.toString()
            if (users == jsonjObject.getString("usuario")) {
                jsonjObject.put("password", passwords)
                jsonjObject.put("nombre", nombres)
                jsonjObject.put("apellidos", apellidos)
            }
            outputStreamWriter.write(jsonjObject.toString())
            outputStreamWriter.close()
            fileOutput.close()
        }

        finish()
    }

    fun onCancelar(view: View) {
        onBackPressed()
    }
}