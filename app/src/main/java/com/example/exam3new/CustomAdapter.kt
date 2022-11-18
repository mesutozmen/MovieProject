package com.example.exam3new


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


internal class CustomAdapter(
    private var movieList: ArrayList<Movie>,
    private var movieClickListener: MovieClickListener,
    private var addOrRemoveFavoriteListener: AddOrDeleteMovie
    ):RecyclerView.Adapter<CustomAdapter.MyViewHolder>(){

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: AppCompatTextView = view.findViewById(R.id.tvName)
        var imageMovie: AppCompatImageView = view.findViewById(R.id.imageMovie)
        var imdb:AppCompatTextView=view.findViewById(R.id.tvImdbID)
        var likeButton: AppCompatCheckBox=view.findViewById(R.id.likeButton)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text=movieList[position].title
        holder.imdb.text=movieList[position].imdbID

        Glide.with(holder.itemView).load(movieList[position].poster)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(30))).into(holder.imageMovie)

        holder.imageMovie.setOnClickListener {
            movieClickListener.clickMovieImage(position)
        }


        holder.likeButton.isChecked = movieList[position].favorite

        holder.likeButton.setOnCheckedChangeListener {_, isChecked ->
            addOrRemoveFavoriteListener.onAddOrRemoveFavorite(position, isChecked)
        }


    }
    override fun getItemCount(): Int {
        return movieList.size
    }






}