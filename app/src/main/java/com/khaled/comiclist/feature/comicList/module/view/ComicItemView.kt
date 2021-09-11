package com.khaled.comiclist.feature.comicList.module.view

import android.os.Parcel
import android.os.Parcelable

data class ComicItemView(
    val number: Int,
    val title: String?,
    val description: String?,
    val imageUrl: String?,
    var isFavorite: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeInt(number)
        parcel?.writeString(title)
        parcel?.writeString(description)
        parcel?.writeString(imageUrl)
        parcel?.writeByte(if (isFavorite) 1 else 0)
    }

    companion object CREATOR : Parcelable.Creator<ComicItemView> {
        override fun createFromParcel(parcel: Parcel): ComicItemView {
            return ComicItemView(parcel)
        }

        override fun newArray(size: Int): Array<ComicItemView?> {
            return arrayOfNulls(size)
        }
    }
}