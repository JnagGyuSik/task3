package com.example.task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class SignInActivity : AppCompatActivity() {
    private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etId = findViewById<EditText>(R.id.etId)
        val etPw = findViewById<EditText>(R.id.etPw)
        val btLogin = findViewById<Button>(R.id.btLogin)
        val btMember = findViewById<Button>(R.id.btMembership)

        //로그인 버튼
        btLogin.setOnClickListener {
            if(etId.text.isBlank() || etPw.text.isBlank()){
                Toast.makeText(this,"아이디/비밀번호를 확인해주세요",Toast.LENGTH_SHORT).show()
            }else{
                if (login(etId.text.toString(), etPw.text.toString())){
                    val intent = Intent(this, HomeActivity::class.java)
                    Log.d("ID확인", "아이디 : ${etId.text}, 이름 : $name")
                    intent.putExtra("id", etId.text.toString())
                    intent.putExtra("name", name)
                    Toast.makeText(this,"로그인에 성공하였습니다",Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                } else{
                    Toast.makeText(this,"아이디 또는 비밀번호가 틀렸습니다.",Toast.LENGTH_SHORT).show()
                }
            }
        }

        //회원가입 버튼
        btMember.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    //로그인 확인
    fun login(id:String, pw:String):Boolean{
        var boolean = true
        for (i in idList.indices){
            if (idList[i] == id && pwList[i] == pw){
                name = nameList[i]
                boolean = true
                break
            }else{
                boolean = false
            }
        }
        return boolean
    }
}