package com.utn.frba.cinemapp.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.models.cine
import kotlinx.android.synthetic.main.item_select_cinema.view.*

class SelectCinemaAdapter(var cines:List<cine>): RecyclerView.Adapter<SelectCinemaAdapter.SelectCinemaHolder>() {

    class SelectCinemaHolder(val view: View): RecyclerView.ViewHolder(view){
        fun render(cine: cine){
            view.select_cinema_adress.text = cine.adress
            view.select_cinema_name.text = cine.name
            view.select_cinema_description.text = cine.description

            //Le agrego el listener para que me envíe a la siguiente pantalla
            view.setOnClickListener(){
                Toast.makeText(it.context, "Seleccionaste el ${cine.name}", Toast.LENGTH_LONG).show()
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