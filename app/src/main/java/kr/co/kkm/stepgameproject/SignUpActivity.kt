package kr.co.kkm.stepgameproject

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        // Initialize Firebase Auth
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

    private fun signUp() {

        val email=findViewById<TextView>(R.id.editEmailText).text.toString()
        val password=findViewById<TextView>(R.id.editEmailText).text.toString()
        val nickname=findViewById<TextView>(R.id.editNickText).text.toString()
        val name=findViewById<TextView>(R.id.editNameText).text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // 회원가입 성공시
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    if (user != null) {
                        val uid = user.uid
                        saveAdditionalUserInfo(uid, name, nickname)
                    }
                    val mPage = Intent(this, MainActivity::class.java)
                    startActivity(mPage)
                } else {
                    // 회원가입 실패시
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "회원 가입 실패", Toast.LENGTH_SHORT,).show()
                }
            }
    }
ㄷ
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