package com.example.study

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.TextView

fun alert(mes:String ,toptitle:String,cont:Context){
    val dialogBuilder = AlertDialog.Builder(cont)
    dialogBuilder.setMessage(mes)

        .setCancelable(false)
    dialogBuilder.setPositiveButton("Got It", DialogInterface.OnClickListener {
            dialog, id ->  dialog.cancel()
    })
    val alert = dialogBuilder.create()
    alert.setTitle(toptitle)
    alert.show()

}
