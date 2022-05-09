package com.example.movielist

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movielist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



        val ListPeliculas: List<Movies> = listOf(
        Movies(
            "Uncharted",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/tlZpSxYuBRoVJBOpUrPdQe9FmFq.jpg",
            "Nathan Drake, un joven astuto en la calle, y su socio bromista Victor Sully Sullivan se embarcan en una peligrosa búsqueda del tesoro más grande jamás encontrado mientras siguen pistas que pueden conducir al hermano perdido de Nathan.",
            "Género: Aventura, Fantasia, Accion, Comedia"
        ),
        Movies(
            "Batman",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/74xTEgt7R36Fpooo50r9T25onhq.jpg",
            "En su segundo año de lucha contra el crimen, Batman descubre la corrupción en Gotham City que conecta a su propia familia mientras se enfrenta a un asesino en serie conocido como Riddler.",
            "Género: Aventura, Crimen, Accion, Drama, Misterio"
        ),
        Movies(
            "Spider-Man: Sin camino a casa",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            "Peter Parker está desenmascarado y ya no puede separar su vida normal de las altas apuestas de ser un superhéroe. Cuando le pide ayuda al Doctor Strange, lo que está en juego se vuelve aún más peligroso y lo obliga a descubrir lo que realmente significa ser Spider-Man.",
            "Género: Acción,  Aventura,  Ciencia Ficción"
        ),
        Movies(
            "sonic el erizo 2",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg",
            "Después de establecerse en Green Hills, Sonic está ansioso por demostrar que tiene lo que se necesita para ser un verdadero héroe. Su prueba llega cuando el Dr. Robotnik regresa, esta vez con un nuevo compañero, Knuckles, en busca de una esmeralda que tiene el poder de destruir civilizaciones. Sonic se une a su propio compañero, Tails, y juntos se embarcan en un viaje por el mundo para encontrar la esmeralda antes de que caiga en las manos equivocadas.",
            "Género: Acción,  Ciencia Ficción,  Comedia,  Familia,  Aventura"
        ),
        Movies(
            "Volviendose rojo",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/qsdjk9oAKSQMWs0Vt5Pyfh6O4GZ.jpg",
            "Mei, de trece años, está experimentando la incomodidad de ser una adolescente con un giro: cuando se emociona demasiado, se transforma en un panda rojo gigante.",
            "Género: Animación,  Familia,  Comedia,  Fantasía"
        ),
        Movies(
            "Virus:32",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/jPIY9B8IWBz6imj972m5FpDpnop.jpg",
            "Se desata un virus y una masacre escalofriante recorre las calles de Montevideo.",
            "Género: Horror"
        ),
        Movies(
            "Ambulancia",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/zT5ynZ0UR6HFfWQSRf2uKtqCyWD.jpg",
            "El veterano condecorado Will Sharp, desesperado por conseguir dinero para cubrir las facturas médicas de su esposa, pide ayuda a su hermano adoptivo Danny. Danny, un carismático criminal de carrera, le ofrece una puntuación: el mayor atraco a un banco en la historia de Los Ángeles: 32 millones de dólares.",
            "Género: Acción,  Suspenso,  Crimen"
        ),
        Movies(
            "Encanto",
            "https://www.themoviedb.org/t/p/w220_and_h330_face/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
            "La historia de una familia extraordinaria, los Madrigal, que viven escondidos en las montañas de Colombia, en una casa mágica, en un pueblo vibrante, en un lugar maravilloso y encantado llamado Encanto. La magia del Encanto ha bendecido a todos los niños de la familia con un regalo único, desde la superfuerza hasta el poder de curar: todos los niños excepto uno, Mirabel. Pero cuando descubre que la magia que rodea al Encanto está en peligro, Mirabel decide que ella, la única Madrigal ordinaria, podría ser la última esperanza de su excepcional familia.",
            "Género: Animación,  Comedia,  Familia,  Fantasía"
        )
    )
    var color:Boolean =false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        binding.switchTema.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (binding.switchTema.isChecked()) {
                updateTheme("DARK", "#212121", "#37474f")
                color=true
            } else {
                updateTheme("DEFAULT", "#FFFFFF", "#FF3700B3")
                color=false
            }
        })
        loadTheme()
    }


    /*Lo que hago aqui es mandar la lista al adapter para que me lo vaya pasando y convirtiendo
    los datos en el item_Peliculas(view o activity) uno por uno. Luego los acomodo  en
    RecyclerView que es una interfaz de tipo lista */
    fun initRecyclerView() {
        binding.rvPeliculas.layoutManager = GridLayoutManager (this,2)
        val adapter = PeliculasAdapter(ListPeliculas) { Movies -> onItemSelected(Movies) }
        binding.rvPeliculas.adapter = adapter
    }

    /*Me permite mandar los datos de la lista cuando yo le doy clik a una celda que contenga una imagen y el
    nombre. Eso para que me muestre toda la informacion de la pelicula en una interfaz a parte */
    fun onItemSelected(movies: Movies) {
        //intent lo uso para saber cual activity quiero usar y madar los datos
        val intent = Intent(this, DescripcionPeliculas::class.java)
        intent.putExtra("imageUrl", movies.Movies)
        intent.putExtra("Nombre",movies.NombrePeliculas)
        intent.putExtra("Descripcion",movies.descripcion)
        intent.putExtra("Genero",movies.genero)
        if(color){
            intent.putExtra("key","DEFAULT")
            intent.putExtra("color1","#212121")
            intent.putExtra("color2","#37474f")
        }else{
            intent.putExtra("key","DARK")
            intent.putExtra("color1","#FFFFFF")
            intent.putExtra("color2","#FF3700B3")
        }

        startActivity(intent)
    }

    fun updateTheme( key:String,Color1:String,Color2:String) {
        //Archivo de configuraciones
        val savePreferences :SharedPreferences = getSharedPreferences("config_theme", MODE_PRIVATE)
        val ObjEditor: SharedPreferences.Editor = savePreferences.edit()
        ObjEditor.putString("theme",key)
        ObjEditor.commit()
        binding.Back.setBackgroundColor(Color1.toColorInt())
        binding.rvPeliculas .setBackgroundColor(Color2.toColorInt())
    }
     fun loadTheme() {
        val loadPreferences = getSharedPreferences("config_theme", MODE_PRIVATE)
        val actualTheme = loadPreferences.getString("theme", "DEFAULT")
        if (actualTheme == "DEFAULT") {
            updateTheme("DEFAULT", "#FFFFFF", "#FF3700B3")
        } else if (actualTheme == "DARK") {
            updateTheme("DARK", "#212121", "#37474f")
            binding.switchTema.setChecked(true)
        }
    }


}