package com.example.task3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

//회원정보
var nameList = mutableListOf<String>()
var idList = mutableListOf<String>()
var pwList = mutableListOf<String>()

class SignUpActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val memberName = findViewById<EditText>(R.id.etName)
        val memberId = findViewById<EditText>(R.id.etId2)
        val memberPw = findViewById<EditText>(R.id.etPw2)
        val memberBt = findViewById<Button>(R.id.btMembership2)

        //회원가입 버튼
        memberBt.setOnClickListener {
            if(memberName.text.isBlank() || memberId.text.isBlank() || memberPw.text.isBlank()){
                Toast.makeText(this,"입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }else{
                //list함수 실행 후 결과가 참일 경우 회원정보를 리스트에 저장
                if (list(memberId.text.toString())){
                    nameList.add(memberName.text.toString())
                    idList.add(memberId.text.toString())
                    pwList.add(memberPw.text.toString())
                    Toast.makeText(this,"회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SignInActivity::class.java).apply {
                        putExtra("SingIn_ID", memberId.text.toString())
                        putExtra("SingIn_PW", memberPw.text.toString())
                    }
                    Log.d("ID확인","이름 : $nameList  아이디 : $idList  비밀번호 : $pwList")
                    setResult(Activity.RESULT_OK, intent)

                    //액티비티 강제 종료
                    if(!isFinishing) {
                        finish()
                    }
                }else{
                    Toast.makeText(this,"중복된 아이디 입니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //중복된 아이디 확인
    fun list(id:String):Boolean{
        var boolean = true
        for (i in nameList.indices){
            if (id == nameList[i]) {
                boolean = false
                break
            }
        }
        return boolean
    }
}

