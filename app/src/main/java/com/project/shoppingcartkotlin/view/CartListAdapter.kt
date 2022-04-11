package com.project.shoppingcartkotlin.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.shoppingcartkotlin.R
import com.project.shoppingcartkotlin.databinding.ItemProductCartBinding
import com.project.shoppingcartkotlin.model.ProductModel
import com.project.shoppingcartkotlin.model.ProductsItem
import com.squareup.picasso.Picasso

class CartListAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var productList = mutableListOf<ProductModel>()
    private var listener: DeleteItem? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setProducts(product: List<ProductModel>, listener:DeleteItem) {
        this.productList = product.toMutableList()
        this.listener = listener
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductCartBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {


        val product = productList[position]

        var ext: String = product.productAmount!!.replace("[^a-zA-Z0-9]", "")



        val re = Regex("[^A-Za-z0-9 ]")
        ext = re.replace(ext, "") // works

        val amt: Int = product.productQuantity!!.toInt() *  ext.toInt()

        holder.binding.productName.text = product.productName
            holder.binding.productAmount.text = "₹$amt"
            holder.binding.productQuantity.text = product.productQuantity
            Picasso.get().load(product.productImage).placeholder(R.drawable.shopping_cart).into(holder.binding.productImage)

            holder.binding.imgDelete.setOnClickListener {
                listener!!.delete(product, position)
            }


    }

    override fun getItemCount(): Int {
        return productList.size
    }


}

class MainViewHolder(val binding: ItemProductCartBinding) : RecyclerView.ViewHolder(binding.root)

interface DeleteItem {
    fun delete(productsItem: ProductModel?, count: Int?)
}