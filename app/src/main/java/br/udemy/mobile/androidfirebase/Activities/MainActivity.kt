package br.udemy.mobile.androidfirebase.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.udemy.mobile.androidfirebase.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"

    private var mAuth: FirebaseAuth? = null

    private var email: String? = null
    private var password: String? = null

    private var edtEmail: EditText? = null
    private var edtSenha: EditText? = null
    private var btnLogin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        initialise()
    }

    private fun initialise() {

        edtEmail = findViewById<View>(R.id.edtEmail) as EditText
        edtSenha = findViewById<View>(R.id.edtSenha) as EditText
        btnLogin = findViewById<View>(R.id.btnLogin) as Button

        mAuth = FirebaseAuth.getInstance()

        btnLogin!!.setOnClickListener { loginUser() }
    }

    private fun loginUser() {
        email = edtEmail?.text.toString()
        password = edtSenha?.text.toString()

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            mAuth?.signInWithEmailAndPassword(email!!, password!!)
                    ?.addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val intent =  Intent(this, CadatroUsuario::class.java)
                            startActivity(intent)
                        } else {
                            Log.e(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(applicationContext, "Autenticação falhou",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }
        } else {
            Toast.makeText(this, "Entre com email e senha", Toast.LENGTH_SHORT).show()
        }
    }

}
