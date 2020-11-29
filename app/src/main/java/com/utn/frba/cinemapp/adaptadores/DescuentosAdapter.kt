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
import com.utn.frba.cinemapp.models.Descuento
import com.utn.frba.cinemapp.models.cine
import com.utn.frba.cinemapp.models.compra
import kotlinx.android.synthetic.main.item_descuentos.view.*
import kotlinx.android.synthetic.main.item_select_cinema.view.*

class DescuentosAdapter(var descuentos:List<Descuento>): RecyclerView.Adapter<DescuentosAdapter.ViewHolder>() {

    var viewHolder: ViewHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DescuentosAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val vista = layoutInflater.inflate(R.layout.item_descuentos,parent,false)
        viewHolder = ViewHolder(vista)
        return viewHolder as ViewHolder
    }

    override fun getItemCount(): Int {
        return descuentos.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = descuentos[position]
        holder.descripcion = item.description

    }

    class ViewHolder(vista: View):RecyclerView.ViewHolder(vista){
        var vista = vista
        var descripcion: String? = null
        init {
            descripcion = vista.descuento_description.toString()
        }
    }
}