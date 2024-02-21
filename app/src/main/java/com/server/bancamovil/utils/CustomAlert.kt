package com.server.bancamovil.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog


class CustomAlert(
    private val context: Context?
) {

    fun showMessageOKCancel(
        message: String,
        okListener: DialogInterface.OnClickListener
    ) {
        AlertDialog.Builder(context!!)
            .setMessage(message)
            .setPositiveButton("Ok", okListener)
            .create()
            .show()
    }

}