package com.khaled.comiclist.feature.module.data

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["title", "number"])])
data class ComicItem(
    @PrimaryKey
    @NonNull
    val number: Int,
    val title: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "image_url")
    val imageUrl: String?,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() > 0
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(number)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(imageUrl)
        parcel.writeByte(if (isFavorite) 1 else 0)
    }

    override fun describeContents() = 0


    companion object CREATOR : Parcelable.Creator<ComicItem> {
        override fun createFromParcel(parcel: Parcel) = ComicItem(parcel)

        override fun newArray(size: Int): Array<ComicItem?> {
            return arrayOfNulls(size)
        }
    }
}