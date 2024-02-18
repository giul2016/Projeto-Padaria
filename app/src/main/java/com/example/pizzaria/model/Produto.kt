package com.example.pizzaria.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.Gson

data class Produto(
    val imgProduct: Int,
    val name: String,
    var price: Double,
    val category: String,
    val descricao: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
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

    override fun hashCode(): Int {
        var result = imgProduct
        result = 31 * result + name.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + descricao.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Produto

        if (imgProduct != other.imgProduct) return false
        if (name != other.name) return false
        if (price != other.price) return false
        if (category != other.category) return false
        if (descricao != other.descricao) return false

        return true
    }

    companion object CREATOR : Parcelable.Creator<Produto> {
        override fun createFromParcel(parcel: Parcel): Produto {
            return Produto(parcel)
        }

        override fun newArray(size: Int): Array<Produto?> {
            return arrayOfNulls(size)
        }

        fun fromJson(json: String): Produto {
            return Gson().fromJson(json, Produto::class.java)
        }
    }
}
