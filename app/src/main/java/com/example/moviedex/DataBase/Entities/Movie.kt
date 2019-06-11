package com.example.moviedex.DataBase.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName="movies")
data class Movie(
    @PrimaryKey
    @field:Json(name="imdbID")
    val imdbID: String = "N/a",
    @field:Json(name="Title")
    val Title:String = "N/A",
    @field:Json(name="Year")
    val Year:String = "N/A",
    @field:Json(name="Released")
    val Released: String = "N/A",
    @field:Json(name="Runtime")
    val Runtime:String = "N/A",
    @field:Json(name="Genre")
    val Genre:String = "N/A",
    @field:Json(name="Director")
    val Director:String = "N/A",
    @field:Json(name="Actors")
    val Actors:String = "N/A",
    @field:Json(name="Plot")
    val Plot:String = "N/A",
    @field:Json(name="Language")
    val Language:String = "N/A",
    @field:Json(name="imdbRating")
    val imdbRating:String = "N/A",
    @field:Json(name="Poster")
    val Poster:String = "N/A" ): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imdbID)
        parcel.writeString(Title)
        parcel.writeString(Year)
        parcel.writeString(Released)
        parcel.writeString(Runtime)
        parcel.writeString(Genre)
        parcel.writeString(Director)
        parcel.writeString(Actors)
        parcel.writeString(Plot)
        parcel.writeString(Language)
        parcel.writeString(imdbRating)
        parcel.writeString(Poster)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}

@Entity(tableName="previews")
data class MoviePreview(
    @field:Json(name="Title")
    val Title: String = "N/A",
    @field:Json(name="Year")
    val Year: String = "N/A",
    @PrimaryKey
    @field:Json(name="imdbID")
    val imdbID: String = "N/A",
    @field:Json(name="Type")
    val Type: String = "N/A",
    @field:Json(name="Poster")
    val Poster: String = "N/A"
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Title)
        parcel.writeString(Year)
        parcel.writeString(imdbID)
        parcel.writeString(Type)
        parcel.writeString(Poster)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MoviePreview> {
        override fun createFromParcel(parcel: Parcel): MoviePreview {
            return MoviePreview(parcel)
        }

        override fun newArray(size: Int): Array<MoviePreview?> {
            return arrayOfNulls(size)
        }
    }
}

data class Search(
    val Search: List<MoviePreview>,
    val totalResults: String,
    val Response: String
)