package com.example.tarea2davidjson

import android.os.Bundle

class Users {
    var usuario: String = ""
    var password: String = ""
    var nombre: String = ""
    var apellido: String = ""

    fun getBundle(): Bundle {
        var bundle = Bundle()
        bundle.putCharSequence("usuario", usuario)
        bundle.putCharSequence("password", password)
        bundle.putCharSequence("nombre", nombre)
        bundle.putCharSequence("apellido", apellido)
        return bundle
    }

    fun setBundle(bundle: Bundle) {
        usuario = bundle.getString("usuario", "")
        password = bundle.getString("password", "")
        nombre = bundle.getString("nombre", "")
        apellido = bundle.getString("apellido", "")
    }

    fun onJson(): String {
        return " { \"usuario\":\"\",\n" + " { \"password\":\"\",\n" + " { \"nombre\":\"\",\n" + " { \"apellidos\":\"\"},\n"
    }
}