package com.project.shoppingcartkotlin.repositary

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.RoomDatabase
import com.project.shoppingcartkotlin.model.ProductModel
import com.project.shoppingcartkotlin.room.ProductDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomRepo {

    companion object {

        var productDatabase: ProductDatabase? = null

        var productModel: LiveData<List<ProductModel>>? = null

        fun initializeDB(context: Context) : ProductDatabase {
            return ProductDatabase.getDataBaseClient(context)
        }

        fun insertData(context: Context, productModel: ProductModel) {

            productDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                productDatabase!!.productDao().InsertData(productModel)
            }

        }

        fun deleteData(context: Context, id:Int?) {

            productDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                productDatabase!!.productDao().deleteByProductId(id)
            }

        }

        fun updateData(context: Context, quantity:String, productId:String) {
            productDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                productDatabase!!.productDao().updateQuantity(quantity,productId)
            }
        }


        fun getAllData(context: Context) : LiveData<List<ProductModel>>?  {

            productDatabase = initializeDB(context)

            productModel = productDatabase!!.productDao().getAll()

            return productModel
        }

    }

}