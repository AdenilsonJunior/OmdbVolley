package br.edu.ifsp.scl.movievolley

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class MovieResponse : Parcelable {

    @SerializedName("Title")
    val title: String = ""
    @SerializedName("Year")
    val year: String = ""
    @SerializedName("Released")
    val released: String = ""
    @SerializedName("Director")
    val director: String = ""
    @SerializedName("Actors")
    val actors: String = ""
    @SerializedName("Language")
    val idiom: String = ""
    @SerializedName("Country")
    val country: String = ""
    @SerializedName("Awards")
    val awards: String = ""
    @SerializedName("Poster")
    val poster: String = ""
    @SerializedName("imdbRating")
    val imdbRation: String = ""
    @SerializedName("Type")
    val type: String = ""
    @SerializedName("Production")
    val production: String = ""
    @SerializedName("Website")
    val website: String = ""

    constructor(source: Parcel) : this()

    constructor()

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MovieResponse> = object : Parcelable.Creator<MovieResponse> {
            override fun createFromParcel(source: Parcel): MovieResponse = MovieResponse(source)
            override fun newArray(size: Int): Array<MovieResponse?> = arrayOfNulls(size)
        }
    }
}