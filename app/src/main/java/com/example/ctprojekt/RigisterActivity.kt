package com.example.ctprojekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class RigisterActivity : AppCompatActivity() {
    private lateinit var etRegisterPassword:EditText
    private lateinit var etRegisterUser:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rigister)
        etRegisterUser = findViewById<EditText>(R.id.etRegisterUser)
        etRegisterPassword = findViewById(R.id.etRegisterPassword)
    }
    fun btnRigisterClicked(view: View){
        when{
            TextUtils.isEmpty(etRegisterUser.text.toString().trim{it <= ' '}) -> {
                Toast.makeText(this@RigisterActivity, "Pleas enter an Email",Toast.LENGTH_SHORT).show()
            }
            TextUtils.isEmpty(etRegisterPassword.text.toString().trim{it <= ' '}) -> {
                Toast.makeText(this@RigisterActivity, "Pleas enter an Email",Toast.LENGTH_SHORT).show()
            }
            else -> {
                val email: String = etRegisterUser.text.toString().trim{it <= ' '}
                val password: String = etRegisterPassword.text.toString().trim{it <= ' '}
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        Toast.makeText(this, "You are Registert", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("user_is", firebaseUser.uid)
                        intent.putExtra("email_id", email)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this,task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

    }
}