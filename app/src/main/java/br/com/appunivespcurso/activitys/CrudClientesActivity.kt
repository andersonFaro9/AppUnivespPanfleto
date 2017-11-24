package br.com.appunivespcurso.activitys

import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import br.com.appunivespcurso.model.ClientModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.list_clientes.*
import java.util.*

/**
 * Created by faro on 11/5/17.
 */
class CrudClientesActivity : AppCompatActivity() {

    private fun valida(): Boolean {
        var check = true
        var email: String? = edit_email.text.toString().trim()

        if (TextUtils.isEmpty(email)) {
            edit_email.error = "Campo de e-mail vazio"
            check = false
        }

        val password: String = edit_password.text.toString().trim()

        if (TextUtils.isEmpty(password) ) {
            edit_password.error = "Campo de senha vazio"

            check = false
        }
        return check
    }



    fun ListClientActivity.addCliente(): Boolean {

        var check = true

        val cliente = ClientModel()

        cliente.uid = UUID.randomUUID().toString()

        cliente.nome = editTextName?.text.toString()

        if (TextUtils.isEmpty(cliente.nome)) {
            editTextName?.error = "Campo de nome vazio"
            check = false
        }

        cliente.email = editTextEmail?.text.toString()

        if (TextUtils.isEmpty(cliente.email)) {
            editTextEmail?.error = "Campo de e-mail vazio"
            check = false
        }

        else {
            databaseReference?.child("Cliente")?.child(cliente.uid)?.setValue(cliente)
        }





        //databaseReference?.child("Cliente")?.child(cliente.uid)?.setValue(cliente)

        Log.d("informação adicionada", "$cliente")

        cleanList()

        return check
    }

    fun ListClientActivity.updateCliente(): Boolean {

        var check = true

        val cliente = ClientModel()

        cliente.uid = clienteSelecionado.uid

        cliente.nome = editTextName?.text.toString().trim()

        if (TextUtils.isEmpty(cliente.nome)) {
            editTextName?.error = "Campo de e-mail vazio"
            check = false
        }

        cliente.email = editTextEmail?.text.toString().trim()

        if (TextUtils.isEmpty(cliente.email)) {
            editTextEmail?.error = "Campo de e-mail vazio"
            check = false
        }

        else {
            databaseReference?.child("Cliente")?.child(cliente.uid)?.setValue(cliente)
        }


        Log.d("informação editada", "$cliente")

        cleanList()

        return check
    }

    fun ListClientActivity.deleteCliente(): Boolean {

        var check = true
        val cliente = ClientModel()
        cliente.uid = clienteSelecionado.uid

        cliente.nome = editTextName?.text.toString().trim()

        if (TextUtils.isEmpty(cliente.nome)) {
            editTextName?.error = "Campo de e-mail vazio"
            check = false
        }

        cliente.email = editTextEmail?.text.toString().trim()

        if (TextUtils.isEmpty(cliente.email)) {
            editTextEmail?.error = "Campo de e-mail vazio"
            check = false
        }

        else {
            databaseReference?.child("Cliente")?.child(cliente.uid)?.removeValue()
        }


        Log.d("informação editada", "$cliente")

        cleanList()

        return check
    }

}