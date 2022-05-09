package com.example.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.databinding.ItemPeliculasBinding
import com.squareup.picasso.Picasso

//Se encarga de convertir la lista de los datos en una vista y muestra la información para mandar al RecyclerView
class PeliculasAdapter(
    val Peliculas: List<Movies>, private val onClickListener: (Movies) -> Unit) :
    RecyclerView.Adapter<PeliculasAdapter.PeliculasViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        /*Creo un view holder o mejor dicho una interfaz donde muestra los datos del item_peliculas
        el cual me permitirá mandar la información de la lista y me permite modificar los campos
        del item_peliculas*/

        return PeliculasViewHolder(layoutInflater.inflate(R.layout.item_peliculas, parent, false))
    }

    /*Lo que hago es que la lista se posicione en x dato para así  mandar la información  a la función render
    debido a que es la encargada de  transferir los datos a otra activity o mejor dicho mostrarlo */
    override fun onBindViewHolder(holder: PeliculasViewHolder, position: Int) {
        holder.render(Peliculas[position], onClickListener)
    }
    /*Obtengo el valor de la cantidad de datos que posee mi lista con el fin de mostrar esa cantidad de datos
    Porque si le pongo 2 aunque mi lista tenga más datos solo me mostrara 2 datos eso es debido a que declaro
    la cantidad de datos que poseo*/

    override fun getItemCount(): Int {
        return Peliculas.size
    }


    //Recive un view(Osea un activity)
    class PeliculasViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        //Me permite colocar la informacion de la lista en la activity que recibo
        val binding = ItemPeliculasBinding.bind(view)
        fun render(movies: Movies, onClickListener: (Movies) -> Unit) {
            binding.tvNombreDePeli.text = movies.NombrePeliculas
            Picasso.get().load(movies.Movies).into(binding.ivPelicula)
            itemView.setOnClickListener { onClickListener(movies) }

        }
    }
}