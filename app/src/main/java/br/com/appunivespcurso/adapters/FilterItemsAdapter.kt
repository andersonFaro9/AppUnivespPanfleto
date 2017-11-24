package br.com.appunivespcurso.adapters

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.util.Log
import br.com.appunivespcurso.model.ShopModel

/**
 * Created by faro on 10/19/17.
 */
open class FilterItemsAdapter : AppCompatActivity(), SearchView.OnQueryTextListener {

    var list = ArrayList<ShopModel>()

    var adapter = ShopAdapter(this, list, list)


    override fun onQueryTextSubmit(query: String?): Boolean {

        val tag = "Script"
        Log.i(tag, "onQueryTextSubmit ${query}")

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.i("Script", "onQueryTextChange ${newText}")

        adapter.filter.filter(newText)
        return true
    }
}