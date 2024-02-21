package com.server.bancamovil.ui.login

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.server.bancamovil.MainActivity
import com.server.bancamovil.R
import com.server.bancamovil.databinding.ActivityLoginBinding
import com.server.bancamovil.utils.CustomAlert
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val snackBar by lazy {
        Snackbar.make(binding.loginActivity, R.string.press_back_again, Snackbar.LENGTH_SHORT)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBackPressedLogin()
        login()
    }



    /** Logueo al sistema **/
    private fun login() {
        binding.btnLogin.setOnClickListener {
            val usuariosContraseñas = mapOf(
                "userTest1" to "passTest1",
                "User@test" to "TestPass_",
                "user123&" to "123456"
            )

            val password = binding.txtPassword.text.toString()
            val username = binding.txtUsername.text.toString()

            if (username.trim().isEmpty() || password.trim().isEmpty()) {
                return@setOnClickListener
            }

            val usuario = usuariosContraseñas.entries.find { it.value == password }?.key
            if (usuario != null) {
                goToMenuActivity()
            }else{
                val customAlert = CustomAlert(this)

                val message = "La contraseña no corresponde al usuario"
                val okListener = DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this, "Acción confirmada", Toast.LENGTH_SHORT).show()
                }
                customAlert.showMessageOKCancel(message, okListener)
            }
        }
    }

    private fun goToMenuActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        finish()
    }

    /** Si presiona regresar **/
    private fun onBackPressedLogin() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (snackBar.isShown) finish() else snackBar.show()
            }
        })
    }
}