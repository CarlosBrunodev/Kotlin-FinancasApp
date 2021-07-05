package com.bruno.financask.ui.activity


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import com.antoni.financask.R


class Homeactivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.homeactivity)


        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val it = Intent(this, ListaTransacoesActivity::class.java)
            startActivity(it)
            finish()
        },3000)
    }
}