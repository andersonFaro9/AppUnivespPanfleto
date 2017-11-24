package br.com.appunivespcurso.activitys

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.appunivespcurso.R
import br.com.appunivespcurso.model.ClientModel
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.list_clientes.*
import java.util.*

/**
 * Created by faro on 11/14/17.
 */


//App adicionado ao googledrive

class ListClientActivity : AppCompatActivity() {

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

        addListaDeDados()

        addChilds()

    }

    private fun addListaDeDados() {

        listV_dados?.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->

            clienteSelecionado = parent?.getItemAtPosition(position) as ClientModel
            editTextName?.setText(clienteSelecionado.nome)
            editTextEmail?.setText(clienteSelecionado.email)


        }
    }


    fun addChilds() = databaseReference?.child("Cliente")?.addValueEventListener(ClientesImplments())

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    fun cleanList() {
        editTextEmail?.setText("")
        editTextName?.setText("")
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.add_cliente -> {

                val crud = CrudClientesActivity()

                crud.apply { addCliente() }


            }

            R.id.update_cliente -> {

                val crud = CrudClientesActivity()
                crud.apply { updateCliente() }

            }

            R.id.delete_cliente -> {


                val crud = CrudClientesActivity()
                crud.apply { deleteCliente() }

            }

            else -> return super.onOptionsItemSelected(item)

        }

        arrayAdapterCliente?.notifyDataSetChanged()

        return true

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