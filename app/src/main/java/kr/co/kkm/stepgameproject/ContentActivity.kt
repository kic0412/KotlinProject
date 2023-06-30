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
            val rPage = Intent(this, MapsActivity::class.java)
            startActivity(rPage)
        }
    }

    private fun profile() {
        val proBtn = findViewById<Button>(R.id.contentProfileButton)

        proBtn.setOnClickListener {
            val pPage = Intent(this, ProfileActivity::class.java)
            startActivity(pPage)
        }
    }

}