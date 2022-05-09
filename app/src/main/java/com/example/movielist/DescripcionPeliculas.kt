package com.example.movielist

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.core.graphics.toColorInt
import com.example.movielist.databinding.ActivityDescripcionPeliculasBinding
import com.example.movielist.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class DescripcionPeliculas : AppCompatActivity() {
   // private lateinit var binding: ActivityDescripcionPeliculasBinding
    private lateinit var binding:ActivityDescripcionPeliculasBinding
    private lateinit var binding1: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescripcionPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras!=null){
            Picasso.get().load(intent.getStringExtra("imageUrl")).into(binding.photoView)
            binding.NombreDeLaPelicula.text=intent.getStringExtra("Nombre")
            binding.Descripcion.text=intent.getStringExtra("Descripcion")
            binding.Genero.text=intent.getStringExtra("Genero")
            val key=intent.getStringExtra("key")
            val color1=intent.getStringExtra("color1")
            val color2=intent.getStringExtra("color2")
            updateTheme(key.toString(),color1.toString(),color2.toString())

        }
        binding.BtnVolver.setOnClickListener {onBackPressed() }
    }
    fun updateTheme( key:String,Color1:String,Color2:String) {
        val savePreferences : SharedPreferences = getSharedPreferences("config_theme", MODE_PRIVATE)
        val ObjEditor: SharedPreferences.Editor = savePreferences.edit()
        ObjEditor.putString("theme",key)
        ObjEditor.commit()
        binding.backDes.setBackgroundColor(Color1.toColorInt())
        binding.color .setBackgroundColor(Color2.toColorInt())

    }


}