package com.project.shoppingcartkotlin.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productList")
class ProductModel  (


    @ColumnInfo(name = "product_id")
    var productID: String? = null,

    @ColumnInfo(name = "product_name")
    var productName: String? = null,

    @ColumnInfo(name = "productAmount")
    var productAmount: String? = null,

    @ColumnInfo(name = "product_description")
    var productDescription: String? = null,

    @ColumnInfo(name = "product_image")
    var productImage: String? = null,

    @ColumnInfo(name = "product_quantity")
    var productQuantity: String? = null

){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

}