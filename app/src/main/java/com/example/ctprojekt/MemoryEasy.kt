package com.example.ctprojekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MemoryEasy : AppCompatActivity() {
    private val myWords = 4
    private var chosenNumber = 0
    private var buttonPair1:String = ""
    private var buttonPair2:String = ""
    private var buttonPair3:String = ""
    private var buttonPair4:String = ""
    private var useWord:String = ""
    private var availableWords = arrayOf("Baum","Haus","Kind","Telefon","Virus","Garten","Kotlin","Student","Dieb","Feuerwehrmann","Anarchist","Pflanze")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_easy)
        getWords();
        findButtonPairs();
    }
    private fun getWords(){
            accessArray()
            buttonPair1 = useWord
            accessArray()
            buttonPair2 = useWord
            accessArray()
            buttonPair3 = useWord
            accessArray()
            buttonPair4 = useWord

    }
    private fun accessArray(){
        chosenNumber = Integer.parseInt(""+Math.random()*availableWords.size)
        checkEmpty()
        availableWords.set(chosenNumber, "")
    }
    private fun checkEmpty(){
        if(chosenNumber.equals("")){
            accessArray()
        } else {
            useWord = availableWords.get(chosenNumber)
        }
    }
    private fun findButtonPairs(){
        val list: MutableList<String> = ArrayList()
        list.add(0, "button11")
        list.add(1, "button22")
        list.add(2, "button21")
        list.add(3, "button22")
        list.add(4, "button31")
        list.add(5, "button32")
        list.add(6, "button41")
        list.add(7, "button42")
    }
}