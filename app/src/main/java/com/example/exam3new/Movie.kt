package com.example.exam3new

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    var favorite: Boolean=false,
    var imdbID: String?="",
    var poster: String?="",
    var title: String?="",
    var year: String?=""

) :java.io.Serializable {
    @PrimaryKey(autoGenerate = true) var id:Int=0


}