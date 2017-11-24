package br.com.appunivespcurso.activitys

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import br.com.appunivespcurso.R
import br.com.appunivespcurso.adapters.ShopAdapter
import br.com.appunivespcurso.model.ShopModel
import java.util.*






class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {



    var list = ArrayList<ShopModel>()
    var adapter = ShopAdapter(this, list, list)
    //var imageModelArrayList: ArrayList<ShopModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        prepareList(list)

        val rView = findViewById<RecyclerView>(R.id.rView) as RecyclerView

        rView.adapter = adapter

        rView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
    }


    fun prepareList(list: ArrayList<ShopModel>) {

        list.add(ShopModel("WalMart", "Aqui tem Black Friday, aproveite os cinco dias de promoção.", R.drawable.walmart))
        list.add(ShopModel("Extra", "Dia dos pais tem preço bom no mercado Extra.", R.drawable.extra))
        list.add(ShopModel("Lojas Americanas", "Mil coisas para comprar, aproveite o máximo.", R.drawable.lojasamericanas))
        list.add(ShopModel("Carrefour", "Aproveite nossas quintas-feiras.", R.drawable.carrefour))

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main_cliente, menu)
        //menu.findItem(R.id.cadeado)

        //val searchView = menu.findItem(R.id.search).actionView as? SearchView

        //searchView?.setOnQueryTextListener(this)



        return true

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.cadeado -> {

                val intent = Intent(this@MainActivity, LoginActivity::class.java)

                startActivity(intent)
                return true
            }


            R.id.about -> {


                val intent = Intent(this@MainActivity, AboutActivity::class.java)

                startActivity(intent)
                return true
            }


        }


        return super.onOptionsItemSelected(item)
    }


    override fun onQueryTextSubmit(query: String): Boolean {

        val tag = "Script"
        Log.i(tag, "onQueryTextSubmit ${query}")

        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {

        Log.i("Script", "onQueryTextChange ${newText}")

//        adapter.filter(listaText)
        adapter.filter.filter(newText)
        return true
    }


}

