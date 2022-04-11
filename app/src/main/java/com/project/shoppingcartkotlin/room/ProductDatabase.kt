package com.project.shoppingcartkotlin.room
import android.content.Context
import androidx.room.*
import com.project.shoppingcartkotlin.model.ProductModel

@Database(entities = [ProductModel::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao() : DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getDataBaseClient(context: Context) : ProductDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, ProductDatabase::class.java, "LOGIN_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}