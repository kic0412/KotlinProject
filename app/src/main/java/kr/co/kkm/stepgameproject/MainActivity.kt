package kr.co.kkm.stepgameproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.ktx.*
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)



        val database = Firebase.database
        val myRef = database.getReference("bbs")
        myRef.child("age").setValue(19)
        myRef.child("name").setValue("scott")
        logIn()
        signUp()
    }

    private fun logIn() {
        val logBtn = findViewById<Button>(R.id.mainLogInButton)

        logBtn.setOnClickListener {
            Toast.makeText(this, "로그인 페이지로", Toast.LENGTH_SHORT).show()
            val lPage = Intent(this, LogInActivity::class.java)
            startActivity(lPage)
        }
    }

    private fun signUp() {
        val signBtn = findViewById<Button>(R.id.mainSignUpButton)

        signBtn.setOnClickListener {
            Toast.makeText(this, "회원 가입 페이지로", Toast.LENGTH_SHORT).show()
            val sPage = Intent(this, SignUpActivity::class.java)
            startActivity(sPage)
        }
    }
}