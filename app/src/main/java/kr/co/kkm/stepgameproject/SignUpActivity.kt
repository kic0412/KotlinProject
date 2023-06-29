package kr.co.kkm.stepgameproject

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.StructMsghdr
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text
import kotlin.math.sign

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        auth = Firebase.auth

        button()
    }

    private fun button() {
        val upBtn = findViewById<Button>(R.id.signUpButton)

        upBtn.setOnClickListener {
            signUp()
        }
    }
    
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
        }
    }

    private fun startToast(msg:String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT,).show()
    }

    private fun signUp() {

        val email = findViewById<TextView>(R.id.editEmailText).text.toString()
        val password = findViewById<TextView>(R.id.editPasswordText).text.toString()
        val passwordCheck = findViewById<TextView>(R.id.editCheckText).text.toString()
        val nickname=findViewById<TextView>(R.id.editNickText).text.toString()
        val name=findViewById<TextView>(R.id.editNameText).text.toString()

        if (email.isNotEmpty() && password.isNotEmpty() && passwordCheck.isNotEmpty()) {
            if (password == passwordCheck) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // 회원가입 성공시
                            val user = auth.currentUser
                            if (user != null) {
                                val uid = user.uid
                                saveAdditionalUserInfo(uid, name, nickname)
                            }
                            startToast("회원가입 성공!")
                            val mPage = Intent(this, MainActivity::class.java)
                            startActivity(mPage)
                        } else {
                            // 회원가입 실패시
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            startToast(task.exception.toString())
                        }
                    }
            } else {
                startToast("비밀번호가 일치하지 않습니다.")
            }
        } else {
            startToast("이메일 또는 비밀번호를 입력해주세요.")
        }
    }

    private fun saveAdditionalUserInfo(userId: String?, name: String, nickname: String) {
        userId?.let { // userId를 nullable로 해줬기 때문에 ?.를 사용
            val userRef = FirebaseDatabase.getInstance().reference.child("users").child(it)
            val userInfo = HashMap<String, String>()
            userInfo["name"] = name
            userInfo["nickname"] = nickname

            userRef.setValue(userInfo)
                .addOnSuccessListener {
                    Log.d(TAG, "Additional user info saved successfully")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Failed to save additional user info", e)
                }
        }
    }
}