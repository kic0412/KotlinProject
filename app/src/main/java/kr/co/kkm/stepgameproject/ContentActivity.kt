package kr.co.kkm.stepgameproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content)

        record()
        profile()
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