package com.example.exam3new

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.exam3new.databinding.ActivityMainBinding
import io.reactivex.internal.util.EmptyComponent
import kotlinx.coroutines.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity(), MovieClickListener,AddOrDeleteMovie{


    private val movieList = ArrayList<Movie>()
    private lateinit var recyclerView: RecyclerView
    lateinit var  binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView= findViewById(R.id.recyclerView)








    }


    override fun onResume() {
        super.onResume()

        movieList.clear()

        readJson()

        val favoriteList = MovieRepository(this).favoriteMovieList

        if (favoriteList != null) {
            for (favoriteMovie in favoriteList) {
                for (movie in movieList) {
                    if (movie.title == favoriteMovie.title && movie.poster == favoriteMovie.poster) {
                        movie.favorite = true
                    }
                }
            }
        }


        val adapter =CustomAdapter(movieList,this,this)
        recyclerView.adapter= adapter


    }



    private fun readJson(){
        try {
            val obj=JSONObject(loadJSONFromAsset())

            val movieArray=obj.getJSONArray("Search")

            for (i in 0 until movieArray.length()) {
                val userDetail=movieArray.getJSONObject(i)


                val model=Movie()

                model.poster=userDetail.getString("Poster")
                model.year=userDetail.getString("Year")
                model.imdbID=userDetail.getString("imdbID")
                model.title=userDetail.getString("Title")

                movieList.add(model)

                //Data Add Room

            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = assets.open("response.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset= Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)

        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }

    override fun onAddOrRemoveFavorite(position: Int, isAdd: Boolean) {
        println("add or başında ${movieList[position].favorite}")

        if (isAdd) {
            movieList[position].favorite = true
            MovieRepository(this).add(movieList[position])
        } else {
            movieList[position].favorite = false
            MovieRepository(this).remove(movieList[position])
        }
        println("add or sonunda ${movieList[position].favorite}")


    }

    override fun clickMovieImage(position: Int) {
        val intent = Intent(this@MainActivity, SelectMovieActivity::class.java)
        val bundle = Bundle().apply {
            putSerializable("movie", movieList[position])
        }
        intent.putExtras(bundle)
        startActivity(intent)

    }


}