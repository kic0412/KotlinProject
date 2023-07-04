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

        logIn()
        signUp()
    }

    private fun logIn() {
        val logBtn = findViewById<Button>(R.id.mainLogInButton)

        logBtn.setOnClickListener {
            val lPage = Intent(this, LogInActivity::class.java)
            startActivity(lPage)
        }
    }

    private fun signUp() {
        val signBtn = findViewById<Button>(R.id.mainSignUpButton)

        signBtn.setOnClickListener {
            val sPage = Intent(this, SignUpActivity::class.java)
            startActivity(sPage)
        }
    }
}