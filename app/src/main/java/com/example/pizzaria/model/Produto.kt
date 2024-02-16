package com.example.pizzaria.model

import android.os.Parcel
import android.os.Parcelable

data class Produto(
//    val name:String,
//    val imgProduct:Int,
//    val price:Double,
//    val category: String,
//    val descricao: String

    val imgProduct:Int,
    val name:String,
    var price:Double,
    val category: String,
    val descricao: String


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeInt(imgProduct)
        parcel.writeString(name)
        parcel.writeDouble(price)
        parcel.writeString(category)
        parcel.writeString(descricao)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Produto> {
        override fun createFromParcel(parcel: Parcel): Produto {
            return Produto(parcel)
        }

        override fun newArray(size: Int): Array<Produto?> {
            return arrayOfNulls(size)
        }
    }
}


//data class Produto(
//    val imgProduct:Int,
//    val name:String,
//    val price:Double,
//    val category: String,
//    val descricao: String
//
//)