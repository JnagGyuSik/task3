package com.example.task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //인텐트 값 받아오기
        val intentId = intent.getStringExtra("id")
        val intentName = intent.getStringExtra("name")
        Log.d("ID확인","home아이디 : $intentId, home이름 : $intentName")

        val idText = findViewById<TextView>(R.id.idTv)
        val nameText = findViewById<TextView>(R.id.nameTv)
        val homeBt = findViewById<Button>(R.id.homeBt)

        idText.text = "아이디 : $intentId"
        nameText.text = "이름 : $intentName"

        //종료 버튼
        homeBt.setOnClickListener {
            finish()
        }
    }
}