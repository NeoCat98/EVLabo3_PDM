package com.example.moviedex

import android.os.Parcel
import android.os.Parcelable

data class Movie (
    val Title:String = "N/A",
    val Year:String = "N/A",
    val Rated:String = "N/A",
    val plot:String = "N/A",
    val Released:String = "N/A",
    val Runtime:String = "N/A",
    val Genre:String = "N/A",
    val Director:String = "N/A",
    val Writer:String = "N/A",
    val Actors:String = "N/A",
    val Awards:String = "N/A",
    val Poster:String = "N/A",
    val Ratings:String = "N/A"
):Parcelable {
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
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Title)
        parcel.writeString(Year)
        parcel.writeString(Rated)
        parcel.writeString(plot)
        parcel.writeString(Released)
        parcel.writeString(Runtime)
        parcel.writeString(Genre)
        parcel.writeString(Director)
        parcel.writeString(Writer)
        parcel.writeString(Actors)
        parcel.writeString(Awards)
        parcel.writeString(Poster)
        parcel.writeString(Ratings)
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