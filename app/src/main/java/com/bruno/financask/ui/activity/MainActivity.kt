package com.bruno.financask.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.antoni.financask.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonCadastrar : Button
    private lateinit var buttonEntrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)

        buttonCadastrar = findViewById(R.id.main_button_cadastrar)
        buttonCadastrar.setOnClickListener(this)

        buttonEntrar = findViewById(R.id.main_button_entrar)
        buttonEntrar.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){

            R.id.main_button_cadastrar -> {
                val it = Intent(this, Homeactivity::class.java)
                startActivity(it)
            }
        }

        when(v?.id){

            R.id.main_button_entrar -> {
                val it = Intent(this, ListaTransacoesActivity::class.java)
                startActivity(it)
            }
        }


    }
}