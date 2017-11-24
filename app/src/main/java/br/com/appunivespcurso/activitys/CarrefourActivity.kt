package br.com.appunivespcurso.activitys

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.appunivespcurso.R

/**
 * Created by faro on 11/17/17.
 */
class CarrefourActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrefour)
        supportActionBar?.setHomeButtonEnabled(true)

    }

}