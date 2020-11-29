package com.utn.frba.cinemapp.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.models.Descuento
import kotlinx.android.synthetic.main.item_descuentos.view.*

class DescuentosAdapter(var descuentos: Array<Descuento>): RecyclerView.Adapter<DescuentosAdapter.ViewHolder>() {

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