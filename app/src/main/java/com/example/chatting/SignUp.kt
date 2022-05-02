package com.example.chatting




import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class SignUp : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtName: EditText
    private lateinit var btnSignUP: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var nObRef:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
        mAuth = Firebase.auth
        edtEmail = findViewById(R.id.signUp_Email)
        edtName = findViewById(R.id.ed_name)
        edtPassword = findViewById(R.id.signUp_Password)
        btnSignUP = findViewById(R.id.signUp_button)
        btnSignUP.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            val name = edtName.text.toString().trim()
            signUp(name,email,password)
        }
    }

    private fun signUp(name:String,email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDataBase(name,email,mAuth.currentUser?.uid!!)
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this@SignUp,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Sign Up failure try again",Toast.LENGTH_LONG).show()

                }
            }
    }

    private fun addUserToDataBase(name: String, email: String, uid: String) {
       nObRef = FirebaseDatabase.getInstance().getReference()
       nObRef.child("user").child(uid).setValue(User(name,email,uid))
    }


}