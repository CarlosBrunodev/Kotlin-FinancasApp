package com.bruno.financask.extension

import java.text.SimpleDateFormat
import java.util.Calendar

fun Calendar.formataParaBrasileiro() : String{
    val formatoDataBrasileiro = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatoDataBrasileiro)
    return format.format(this.time)
}