package br.com.appunivespcurso.activitys

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import br.com.appunivespcurso.R
import br.com.appunivespcurso.adapters.ShopAdapter
import br.com.appunivespcurso.model.ShopModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


 open class MainActivity : AppCompatActivity() {

    var list = ArrayList<ShopModel>()
    var adapter = ShopAdapter(this, list)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prepareList(list)

        //val rView = findViewById<RecyclerView>(R.id.rView) as RecyclerView

        rView.adapter = adapter

        rView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
    }


    private fun prepareList(list: ArrayList<ShopModel>) {

        list.add(ShopModel("WalMart", "Aqui tem Black Friday, aproveite os cinco dias de promoção.", R.drawable.walmart))
        list.add(ShopModel("Extra", "Dia dos pais tem preço bom no mercado Extra.", R.drawable.extra))
        list.add(ShopModel("Lojas Americanas", "Mil coisas para comprar, aproveite o máximo.", R.drawable.lojasamericanas))
        list.add(ShopModel("Carrefour", "Aproveite nossas quintas-feiras.", R.drawable.carrefour))

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main_cliente, menu)
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


}

