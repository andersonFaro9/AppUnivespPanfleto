package br.com.appunivespcurso.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import br.com.appunivespcurso.R
import br.com.appunivespcurso.activitys.CarrefourActivity
import br.com.appunivespcurso.activitys.ExtraActivity
import br.com.appunivespcurso.activitys.LojasAmericanasActivity
import br.com.appunivespcurso.activitys.WallMartActivity
import br.com.appunivespcurso.model.ShopModel


/**
 * Created by faro on 9/22/17.
 */

class ShopAdapter(private var context: Context, private var list: List<ShopModel>)
    : RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var titleTextView: TextView? = null
        var thumbImageView: ImageView? = null
        var subtitleTextView: TextView? = null

        init {
            titleTextView = itemView.findViewById<TextView>(R.id.title) as? TextView
            subtitleTextView = itemView.findViewById<TextView>(R.id.subtituloItem) as? TextView
            thumbImageView = itemView.findViewById<ImageView>(R.id.thumbnail) as? ImageView
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent, false);
        val card = view.findViewById<CardView>(R.id.card_view) as CardView

        card.maxCardElevation = 2.0F;

        card.radius = 5.0F;
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var album: ShopModel = list.get(position)

        holder.titleTextView?.text = album.name

        holder.subtitleTextView?.text = album.subTitle

        holder.thumbImageView?.setImageResource(album.thumbnail)

        holder.thumbImageView?.setOnClickListener {

            when (position) {

                0 -> context.startActivity(Intent(context, WallMartActivity::class.java))

                1 -> context.startActivity(Intent(context, ExtraActivity::class.java))

                2 -> context.startActivity(Intent(context, LojasAmericanasActivity::class.java))

                3 -> context.startActivity(Intent(context, CarrefourActivity::class.java))

            }
        }

    }


    override fun getItemCount(): Int = list.size

    private fun ShopAdapter.toast(context: Context, text: CharSequence, duration: Int) {
        Toast.makeText(context, text, duration).show()
    }
}
