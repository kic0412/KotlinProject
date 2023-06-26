package kr.co.kkm.stepgameproject

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        val sign_btn = findViewById<Button>(R.id.signUpButton)

        sign_btn.setOnClickListener {
            Log.e(TAG, "회원가입 버튼 클릭")
            Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
        }
    }
}