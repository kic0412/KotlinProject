package kr.co.kkm.stepgameproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in)

        logIn()
    }

    private fun logIn() {
        val inBtn = findViewById<Button>(R.id.logInButton)

        inBtn.setOnClickListener {
            Toast.makeText(this, "로그인 완료", Toast.LENGTH_SHORT).show()
            val cPage = Intent(this, ContentActivity::class.java)
            startActivity(cPage)
        }
    }
}