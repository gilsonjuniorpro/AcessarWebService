package br.acessar.com.pojo

import android.os.Parcel
import android.os.Parcelable

class Retorno(var code: Int, var msg: String, var list: List<Post>) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            listOf<Post>().apply {
                parcel.readTypedList(this, Post.CREATOR)
            })


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(code)
        parcel.writeString(msg)
        parcel.writeList(list)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Retorno> {
        override fun createFromParcel(parcel: Parcel): Retorno {
            return Retorno(parcel)
        }

        override fun newArray(size: Int): Array<Retorno?> {
            return arrayOfNulls(size)
        }
    }


}