package com.example.task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class HomeActivity : AppCompatActivity() {

    lateinit var homeImage:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeImage = findViewById(R.id.ramdumImage)
        //인텐트 값 받아오기
        val intentId = intent.getStringExtra("id")
        val intentName = intent.getStringExtra("name")
        Log.d("ID확인","home아이디 : $intentId, home이름 : $intentName")

        val idText = findViewById<TextView>(R.id.idTv)
        val nameText = findViewById<TextView>(R.id.nameTv)
        val homeBt = findViewById<Button>(R.id.homeBt)

        randomImage()

        idText.text = "아이디 : $intentId"
        nameText.text = "이름 : $intentName"

        //종료 버튼
        homeBt.setOnClickListener {
            finish()
        }
    }

    //랜덤 이미지 넣기
    private fun randomImage(){
        var num = (1..5)
        val ramdomImg = when(num.random()){
            1 -> R.drawable.image1
            2 -> R.drawable.image2
            3 -> R.drawable.image3
            4 -> R.drawable.image4
            else -> R.drawable.image5
        }
        homeImage.setImageResource(ramdomImg)
    }
}