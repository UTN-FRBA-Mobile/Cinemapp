package com.utn.frba.cinemapp.adaptadores

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.Select_cinema
import com.utn.frba.cinemapp.Select_seat_time
import com.utn.frba.cinemapp.models.cine
import com.utn.frba.cinemapp.models.compra
import kotlinx.android.synthetic.main.item_select_cinema.view.*

class SelectCinemaAdapter(var cines:List<cine>): RecyclerView.Adapter<SelectCinemaAdapter.SelectCinemaHolder>() {

    class SelectCinemaHolder(val view: View): RecyclerView.ViewHolder(view){
        fun render(cine: cine){
            view.select_cinema_adress.text = cine.adress
            view.select_cinema_name.text = cine.name
            view.select_cinema_description.text = cine.description

            //Le agrego el listener para que me envíe a la siguiente pantalla
            view.setOnClickListener(){
                var compraTicket: compra = compra(cine.identificador)
                val selectSeat = Intent(it.context, Select_seat_time::class.java).apply {
//                    var bundle: Bundle = Bundle().putSerializable("compra",compraTicket)
//                    bundle.putSerializable("compra", compraTicket)
                }
                selectSeat.putExtra("compra",compraTicket)
                it.context.startActivity(selectSeat);

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectCinemaHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SelectCinemaHolder(layoutInflater.inflate(R.layout.item_select_cinema,parent,false))
    }

    override fun getItemCount(): Int {
        return cines.size;
    }

    override fun onBindViewHolder(holder: SelectCinemaHolder, position: Int) {
        holder.render(cines[position])

    }
}