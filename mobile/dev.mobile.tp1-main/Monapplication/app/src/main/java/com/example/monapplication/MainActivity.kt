package com.example.monapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val res=findViewById<EditText>(R.id.editTextTextPersonName3)
        val num0=findViewById<Button>(R.id.button)
        val num1=findViewById<Button>(R.id.button11)
        val num2=findViewById<Button>(R.id.button6)
        val num3=findViewById<Button>(R.id.button15)
        val num4=findViewById<Button>(R.id.button10)
        val num5=findViewById<Button>(R.id.button7)
        val num6=findViewById<Button>(R.id.button14)
        val num7=findViewById<Button>(R.id.button12)
        val num8=findViewById<Button>(R.id.button8)
        val num9=findViewById<Button>(R.id.button13)
        val sup=findViewById<Button>(R.id.button17)

        var numbuttons= mutableListOf<Button>(num0,num1,num2,num3,num4,num5,num6,num7,num8,num9)

        sup.setOnClickListener {
            val text: String = res.text.toString()
            res.setText(text.substring(0, text.length - 1))
        }
        for ((i,but) in numbuttons.withIndex()){
            but.setOnClickListener {
                res.text.append(i.toString())
            }
        }
    }
}