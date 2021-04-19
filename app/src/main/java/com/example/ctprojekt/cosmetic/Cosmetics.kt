package com.example.ctprojekt.cosmetic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.ctprojekt.R

class Cosmetics : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cosmetics)
        findViewById<Button>(R.id.btnCosmeticSelection).setOnClickListener(this)
        findViewById<Button>(R.id.btnCosmeticShop).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnCosmeticSelection -> startActivity(Intent(this, CosmeticSelection::class.java))
            R.id.btnCosmeticShop -> startActivity(Intent(this, CosmeticShop::class.java))
        }
    }
}