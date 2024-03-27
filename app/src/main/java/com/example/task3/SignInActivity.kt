package com.example.task3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class SignInActivity : AppCompatActivity() {
    private var name = ""
    lateinit var etId:EditText
    lateinit var etPw:EditText

    //데이터 가져오기 CallBack(액티비티 시작 시 실행)
    var resultString = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if (result.resultCode == Activity.RESULT_OK){ //SignUpActivity의 setResult에 넣은 데이터와 Activity.RESULT_OK 비교
            val signInId = result.data?.getStringExtra("SingIn_ID")
            val signInPw = result.data?.getStringExtra("SingIn_PW")
            Log.d("ID확인","아이디 : $signInId  비밀번호 : $signInPw")
            etId.setText(signInId)
            etPw.setText(signInPw)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etId = findViewById(R.id.etId)
        etPw = findViewById(R.id.etPw)
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
                    intent.putExtra("id", etId.text.toString()) //EditText에 있는 값을 넘겨줄 때 형변환 해주지 않으면 null값이 넘겨진다
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
            //데이터 나타내기
            var showStringIntent = Intent(this, SignUpActivity::class.java)
            resultString.launch(showStringIntent)
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