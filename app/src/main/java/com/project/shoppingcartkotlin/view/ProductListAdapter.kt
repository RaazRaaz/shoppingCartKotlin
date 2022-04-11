package com.project.shoppingcartkotlin.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.shoppingcartkotlin.R
import com.project.shoppingcartkotlin.databinding.ItemProductListBinding
import com.project.shoppingcartkotlin.model.ProductModel
import com.project.shoppingcartkotlin.model.ProductsItem
import com.squareup.picasso.Picasso

class ProductListAdapter : RecyclerView.Adapter<MainViewHolderProduct>() {

    private var productList = mutableListOf<ProductsItem>()
    private var countListener: ProductCountListener? = null
    private var productModel = mutableListOf<ProductModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setProducts(
        product: List<ProductsItem>,
        productModel: List<ProductModel>,
        listener: ProductCountListener
    ) {
        this.productList = product.toMutableList()
        this.countListener = listener
        this.productModel = productModel.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolderProduct {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductListBinding.inflate(inflater, parent, false)
        return MainViewHolderProduct(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolderProduct, position: Int) {

        var i: Int
        val j = 0
        val product = productList[position]

            holder.binding.productName.text = product.name
            holder.binding.productDescription.text = product.description

        Picasso.get().load(product.image).placeholder(R.drawable.shopping_cart).into(holder.binding.productImage)

        for (db in productModel) {
            if (db.productID!! == product.id) {
                holder.binding.productCount.text = db.productQuantity
            }
        }

        holder.binding.productIncrement.setOnClickListener {
            i = holder.binding.productCount.text.toString().toInt()
            if (i >= 0) {
                i++
                holder.binding.productCount.text = "" + i
                countListener!!.addProduct(product, i)
            }
        }

        holder.binding.productDecrement.setOnClickListener {
            i = holder.binding.productCount.text.toString().toInt()
            if (i > 0) {
                if (j < i) {
                    i--
                    holder.binding.productCount.text = "" + i
                    countListener!!.deleteProduct(product, i)
                }
            }
        }


    }

    override fun getItemCount(): Int {
        return productList.size
    }


}

class MainViewHolderProduct(val binding:  ItemProductListBinding) : RecyclerView.ViewHolder(binding.root)


interface ProductCountListener {
    fun addProduct(productsItem: ProductsItem?, count: Int)
    fun deleteProduct(productsItem: ProductsItem?, count: Int)
}