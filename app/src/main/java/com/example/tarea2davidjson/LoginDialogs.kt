package com.example.tarea2davidjson

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class LoginDialogs : DialogFragment() {
    lateinit var users: String
    lateinit var password: String
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var viewDialog = inflater.inflate(R.layout.activity_login_dialogs, container, false)
        var bottomLogin2 = viewDialog.findViewById<Button>(R.id.buttonEntrar)
        bottomLogin2.setOnClickListener { view -> onLogin(view) }
        return viewDialog
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ActionBar.LayoutParams.WRAP_CONTENT)
    }

    fun onLogin(view: View) {
        var et_user = dialog!!.findViewById<EditText>(R.id.editTextTextNombre)
        var et_pwd = dialog!!.findViewById<EditText>(R.id.editTextPwd)
        var user = et_user.text.toString()
        var pwd = et_pwd.text.toString()
        if (user.equals(users) and pwd.equals(password, ignoreCase = true)) {
            var usuario = Users()
            usuario.usuario = user
            mainActivity.onActivarInformacion()
            dismiss()
        } else {
            Toast.makeText(this.activity, "USUARIO/CONTRASEÑA INCORRECTA", Toast.LENGTH_LONG).show()
        }
    }
}