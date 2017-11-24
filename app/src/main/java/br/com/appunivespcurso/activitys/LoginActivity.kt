package br.com.appunivespcurso.activitys


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import br.com.appunivespcurso.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*




class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.setHomeButtonEnabled(true)

        auth = FirebaseAuth.getInstance()

        init()

    }

    override fun onClick(view: View?) {

        when (view) {

            bt_login -> {

                val email: String = edit_email.text.toString().trim()
                val password: String = edit_password.text.toString().trim()

                if (!valida()) {
                    return
                }

                login(email, password)


                // valida()

                //toast(this, "login", Toast.LENGTH_SHORT)
            }

//            bt_registre -> {
//
//                val email: String = edit_email.text.toString().trim()
//                val password: String = edit_password.text.toString().trim()
//
//                if (!valida()) {
//                    return
//                }
//
//                //registre(email, password)
//
//                //toast(this,"registre",Toast.LENGTH_SHORT )
//            }
        }
    }

    private fun valida(): Boolean {
        var check = true
        var email: String = edit_email.text.toString().trim()

        if (TextUtils.isEmpty(email)) {
            edit_email.error = "Campo de e-mail vazio"
            check = false
        }

        val password: String = edit_password.text.toString().trim()

        if (TextUtils.isEmpty(password)) {
            edit_password.error = "Campo de senha vazio"

            check = false
        }
        return check
    }


    private fun registre(email: String, password: String) {

        auth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener {

            if (it.isSuccessful) toast(this, "registro com sucesso!!!", Toast.LENGTH_SHORT) else toast(this, "registro falhou!!!", Toast.LENGTH_SHORT)

        }

    }


    private fun login(email: String, password: String) {

        auth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener {

            if (it.isSuccessful){
                route()

            } else toast(this, "Verifique seu usuário e senha", Toast.LENGTH_SHORT)

        }

    }

    private fun route() {
        toast(this, "Sucesso!!!", Toast.LENGTH_SHORT)
        startActivity(Intent(LoginActivity@ this, ListClientActivity::class.java))
    }




    private fun init() {
        bt_login.setOnClickListener(this)
        //bt_registre.setOnClickListener(this)
    }

    fun LoginActivity.toast(context: Context, text: CharSequence, duration: Int) {
        Toast.makeText(context, text, duration).show()
    }

}

