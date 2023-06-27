package kr.co.kkm.stepgameproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.record)

        content()
    }

    private fun content() {
        val conBtn = findViewById<Button>(R.id.recodeContentButton)

        conBtn.setOnClickListener {
            Toast.makeText(this, "수정 완료", Toast.LENGTH_SHORT).show()
            val cPage = Intent(this, ContentActivity::class.java)
            startActivity(cPage)
        }
    }
}