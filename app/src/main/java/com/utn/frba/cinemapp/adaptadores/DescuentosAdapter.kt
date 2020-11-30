package com.utn.frba.cinemapp.adaptadores

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.models.Descuento
import kotlinx.android.synthetic.main.activity_descuentos_item.view.*

class DescuentosAdapter(
    private var listDescuentos: Array<Descuento>,
    private var context: Context
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater: View = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_descuentos_item,
            parent,
            false
        )

        return ViewHolder(inflater, this.context)
    }

    override fun getItemCount(): Int {
        return listDescuentos.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listDescuentos[position])
    }
}

class ViewHolder(
    private var vista: View,
    private var context: Context
) : RecyclerView.ViewHolder(vista) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(descuento: Descuento) {
        vista.descripcion.text = descuento.description

    }
}