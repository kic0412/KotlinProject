package kr.co.kkm.stepgameproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ContentActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content)

        auth = Firebase.auth

        if (FirebaseAuth.getInstance().currentUser == null)
            startMainActivity()

        ranking()
        record()
        profile()
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun ranking() {
        val ranBtn = findViewById<Button>(R.id.contentRankButton)

        ranBtn.setOnClickListener {
            Firebase.auth.signOut()
            startMainActivity()
        }
    }

    private fun record() {
        val recBtn = findViewById<Button>(R.id.contentRecodeButton)

        recBtn.setOnClickListener {
            Toast.makeText(this, "내 기록으로", Toast.LENGTH_SHORT).show()
            val rPage = Intent(this, RecordActivity::class.java)
            startActivity(rPage)
        }
    }

    private fun profile() {
        val proBtn = findViewById<Button>(R.id.contentProfileButton)

        proBtn.setOnClickListener {
            Toast.makeText(this, "프로필 설정으로", Toast.LENGTH_SHORT).show()
            val pPage = Intent(this, ProfileActivity::class.java)
            startActivity(pPage)
        }
    }

}