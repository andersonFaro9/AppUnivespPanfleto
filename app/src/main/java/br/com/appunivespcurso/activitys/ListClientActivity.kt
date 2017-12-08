package br.com.appunivespcurso.activitys

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.appunivespcurso.R
import br.com.appunivespcurso.interfaces.ICleanListClientActivity
import br.com.appunivespcurso.interfaces.ICrudListClientActivity
import br.com.appunivespcurso.model.ClientModel
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.list_clientes.*
import java.util.*

/**
 * Created by faro on 11/14/17.
 */

open class ListClientActivity : AppCompatActivity(), ICleanListClientActivity, ICrudListClientActivity {

    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null

    private val listaDeClientes = ArrayList<ClientModel>()
    private var arrayAdapterCliente: ArrayAdapter<ClientModel>? = null

    var clienteSelecionado = ClientModel()

    fun initializeFireBase() {

        FirebaseApp.initializeApp(this)

        firebaseDatabase?.setPersistenceEnabled(true)

        firebaseDatabase = FirebaseDatabase.getInstance()

        databaseReference = firebaseDatabase?.reference


    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.list_clientes)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        initializeFireBase()

        adicionaNomeEemailNoBanco()

        addChilds()

    }

    private fun adicionaNomeEemailNoBanco() {

        listV_dados?.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->

            clienteSelecionado = parent?.getItemAtPosition(position) as ClientModel
            editTextName?.setText(clienteSelecionado.nome)
            editTextEmail?.setText(clienteSelecionado.email)

        }
    }


    private fun addChilds() = databaseReference?.child("Cliente")?.addValueEventListener(ClientesImplments())

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun ListClientActivity.cleanList() {

        editTextEmail?.setText("")
        editTextName?.setText("")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.add_cliente -> {

               addCliente()

            }

            R.id.update_cliente -> {


                updateCliente()


            }

            R.id.delete_cliente -> {


                deleteCliente()

            }

            else -> return super.onOptionsItemSelected(item)

        }

        arrayAdapterCliente?.notifyDataSetChanged()

        return true

    }


    override fun ListClientActivity.addCliente(): Boolean {

        var check = true

        val cliente = ClientModel()

        cliente.uid = UUID.randomUUID().toString()

        cliente.nome = editTextName?.text.toString()

        cliente.email = editTextEmail?.text.toString()


        if (TextUtils.isEmpty(cliente.nome) || TextUtils.isEmpty(cliente.email)) {

            Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show()
            check = false
        }

        else {

            databaseReference?.child("Cliente")?.child(cliente.uid)?.setValue(cliente)
        }

        cleanList()

        return check
    }

    override fun ListClientActivity.updateCliente() : Boolean {

        var check = true

        val cliente = ClientModel()

        cliente.uid = clienteSelecionado.uid

        cliente.nome = editTextName?.text.toString()

        cliente.email = editTextEmail?.text.toString()


        if (TextUtils.isEmpty(cliente.nome) || TextUtils.isEmpty(cliente.email)) {

            Toast.makeText(this, "Campos devem está preenchidos", Toast.LENGTH_SHORT).show()
            check = false
        }

        else {
            databaseReference?.child("Cliente")?.child(cliente.uid)?.setValue(cliente)
        }

        return check
    }

    override fun ListClientActivity.deleteCliente() : Boolean {

        var check = true
        val cliente = ClientModel()
        cliente.uid = clienteSelecionado.uid

        cliente.nome = editTextName?.text.toString().trim()
        cliente.email = editTextEmail?.text.toString()

        if (TextUtils.isEmpty(cliente.nome) || TextUtils.isEmpty(cliente.email)) {

            Toast.makeText(this, "Nada deletado", Toast.LENGTH_SHORT).show()
            check = false
        }

        else {
            databaseReference?.child("Cliente")?.child(cliente.uid)?.removeValue()
        }

        cleanList()

        return check
    }









    inner class ClientesImplments : ValueEventListener {

        override fun onDataChange(dataSnapshot: DataSnapshot?) {
            listaDeClientes.clear()

            for (d in dataSnapshot?.children!!) {
                val cliente = d.getValue(ClientModel::class.java)

                listaDeClientes.add(cliente!!)

                arrayAdapterCliente =
                        ArrayAdapter<ClientModel>(this@ListClientActivity, android.R.layout.simple_list_item_1,
                                listaDeClientes)

                listV_dados?.adapter = arrayAdapterCliente

            }
        }

        override fun onCancelled(databaseError: DatabaseError?) {

            Log.w("", "Failed to read value.", databaseError?.toException())
            Toast.makeText(this@ListClientActivity, "Erro", Toast.LENGTH_SHORT).show()
        }


    }
}