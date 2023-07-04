package kr.co.kkm.stepgameproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content)

        map()
        record()
        setting()
    }

    private fun map() {
        val mapBtn = findViewById<Button>(R.id.contentMapBtn)

        mapBtn.setOnClickListener {
            val mPage = Intent(this, MapsActivity::class.java)
            startActivity(mPage)
        }

    }

    private fun record() {
        val recBtn = findViewById<Button>(R.id.contentRecodeBtn)

        recBtn.setOnClickListener {
            val rPage = Intent(this, RecordActivity::class.java)
            startActivity(rPage)
        }
    }

    private fun setting() {
        val setBtn = findViewById<Button>(R.id.contentSettingBtn)

        setBtn.setOnClickListener {
            val sPage = Intent(this, ProfileActivity::class.java)
            startActivity(sPage)
        }
    }
}