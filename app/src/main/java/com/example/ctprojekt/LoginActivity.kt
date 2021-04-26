package com.example.ctprojekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    lateinit var etLoginUser: EditText
    lateinit var etPasswordUser: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var tvRegister = findViewById<TextView>(R.id.tvRegister)
        etLoginUser = findViewById<EditText>(R.id.etUserLogin)
        etPasswordUser = findViewById<EditText>(R.id.etLoginPassword)
        tvRegister.setOnClickListener{tvRegisterClicked()}
    }
    fun tvRegisterClicked(){
        intent = Intent(this, RigisterActivity::class.java)
        startActivity(intent)
    }
    fun btnLoginClicked(view: View){
        when{
            TextUtils.isEmpty(etLoginUser.text.toString().trim{it <= ' '}) -> {
                Toast.makeText(this, "Pleas enter an Email", Toast.LENGTH_SHORT).show()
            }
            TextUtils.isEmpty(etPasswordUser.text.toString().trim{it <= ' '}) -> {
                Toast.makeText(this, "Pleas enter an Email", Toast.LENGTH_SHORT).show()
            }
            else -> {
                val email: String = etLoginUser.text.toString().trim{it <= ' '}
                val password: String = etPasswordUser.text.toString().trim{it <= ' '}
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        Toast.makeText(this, "You are logged in", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("user_is", firebaseUser.uid)
                        intent.putExtra("email_id", email)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this,task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

    }
}