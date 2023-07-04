package kr.co.kkm.stepgameproject

import android.annotation.SuppressLint
import kr.co.kkm.stepgameproject.ContentActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class LogInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    companion object { private const val TAG = "LoginActivity" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in)

        auth = Firebase.auth

        button()
    }

    private fun button() {
        val inBtn = findViewById<Button>(R.id.logInButton)

        inBtn.setOnClickListener {
            logIn()
        }
    }

    private fun startToast(msg:String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT,).show()
    }

    private fun logIn() {

        val email = findViewById<TextView>(R.id.logInEmailText).text.toString()
        val password = findViewById<TextView>(R.id.logInPasswordText).text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // 로그인 성공 시 유저 정보 불러오기
                        val user = auth.currentUser
                        if (user != null) {
                            val uid = user.uid
                            startContentActivity(uid)
                        }
                        startToast("로그인 성공!")
                    } else {
                        // 로그인 실패 시 메세지 알람
                        startToast("이메일 또는 비밀번호를 다시 확인해주세요")
                    }
                }
        }
    }

    private fun startContentActivity(uid: String) {
        val userRef = FirebaseDatabase.getInstance().reference.child("users").child(uid)
        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // dataSnapshot에서 추가 사용자 정보 검색
                val name = dataSnapshot.child("name").value as String
                val nickname = dataSnapshot.child("nickname").value as String

                // 추가 정보를 ContentActivity로 전달
                val intent = Intent(this@LogInActivity, ContentActivity::class.java)
                intent.putExtra("uid", uid)
                intent.putExtra("name", name)
                intent.putExtra("nickname", nickname)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // 에러 처리
                Log.w(TAG, "데이터베이스에서 사용자 정보를 가져오지 못했습니다.", databaseError.toException())
                startToast("사용자 정보를 가져오지 못했습니다!")
            }
        })
    }
}