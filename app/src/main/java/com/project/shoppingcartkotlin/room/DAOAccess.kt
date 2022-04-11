package com.project.shoppingcartkotlin.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.shoppingcartkotlin.model.ProductModel
import com.project.shoppingcartkotlin.viewmodel.ProductViewModel

@Dao
interface DAOAccess {

    @Insert
    fun InsertData(productModel: ProductModel)


    @Query("SELECT * FROM productList")
    fun getAll(): LiveData<List<ProductModel>>?

    @Query("UPDATE productList SET product_quantity = :quantity  WHERE product_id = :pid")
    fun updateQuantity(quantity: String?, pid: String?)


    @Query("DELETE FROM productList")
    fun nukeTable()

    @Query("DELETE FROM productList WHERE id = :id")
    fun deleteByProductId(id: Int?)
}