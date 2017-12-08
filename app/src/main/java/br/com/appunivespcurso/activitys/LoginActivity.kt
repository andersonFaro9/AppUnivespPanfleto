package br.com.appunivespcurso.activitys

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText
import br.com.appunivespcurso.R
import br.com.appunivespcurso.interfaces.ILoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


open class LoginActivity : AppCompatActivity(), View.OnClickListener, ILoginActivity {

    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        supportActionBar?.setHomeButtonEnabled(true)

        auth = FirebaseAuth.getInstance()

        initLogin()


    }

    private fun initLogin() = bt_login.setOnClickListener(this)

    override fun onClick(view: View?) {

        when (view) {
            bt_login -> validaLoginEsenha()
        }
    }


    override fun validaLoginEsenha(): Boolean {

        var check = true
        var email: String = edit_email.text.toString().trim()

        val password: String = edit_password.text.toString().trim()

        when {

            TextUtils.isEmpty(email) || TextUtils.isEmpty(password) -> {

                edit_email.error = " Preencha email corretamente"

                edit_password.error = "Preencha senha corretamente"

                check = false

            }

            else -> login(email, password)
        }

        return check


    }


    private fun login(email: String, password: String) {

        auth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener {

            if (it.isSuccessful) goListClientActivity() else toast(this, "Verifique seu usuário e senha!", Toast.LENGTH_SHORT)

        }

    }

    fun AppCompatActivity.toast(context: Context, text: CharSequence, duration: Int) = makeText(context, text, duration).show()

    private fun goListClientActivity() = startActivity(Intent(LoginActivity@ this, ListClientActivity::class.java))


    private fun registre(email: String, password: String) {

        auth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener {

            if (it.isSuccessful) toast(this, "registro com sucesso!!!", Toast.LENGTH_SHORT) else toast(this, "registro falhou!!!", Toast.LENGTH_SHORT)

        }

    }


}

