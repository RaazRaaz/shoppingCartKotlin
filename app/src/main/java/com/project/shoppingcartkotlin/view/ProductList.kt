package com.project.shoppingcartkotlin.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.shoppingcartkotlin.MyViewModelFactory
import com.project.shoppingcartkotlin.databinding.ActivtyProductBinding
import com.project.shoppingcartkotlin.model.ProductModel
import com.project.shoppingcartkotlin.model.ProductsItem
import com.project.shoppingcartkotlin.repositary.Repository
import com.project.shoppingcartkotlin.services.APIService
import com.project.shoppingcartkotlin.viewmodel.ProductViewModel

class ProductList :AppCompatActivity(), ProductCountListener {
    private lateinit var viewModel: ProductViewModel
    private val adapter = ProductListAdapter()
    private lateinit var binding:ActivtyProductBinding
    private var productModel:List<ProductModel> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivtyProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val service = APIService.getInstance()
        val repo= Repository(service)
        binding.recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this, MyViewModelFactory(repo)).get(ProductViewModel::class.java)
        viewModel.getAllProducts()



        viewModel.getDbProduct(this)!!.observe(this, Observer {
            if (it.isNotEmpty()) {
                productModel = it
                binding.cartCountBadgeTxt.visibility = View.VISIBLE
                binding.cartCountBadgeTxt.text = "" + it.size
            } else {
                binding.cartCountBadgeTxt.visibility = View.GONE
            }
        })

        viewModel.productList.observe(this) {
            adapter.setProducts(it.products as List<ProductsItem>,productModel, this)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.loading.observe(this, {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        binding.cart.setOnClickListener{
            startActivity(Intent(this, CartList::class.java))
            finish()
        }
    }

    override fun addProduct(productsItem: ProductsItem?, count: Int) {
       if(count ==1){
           val productModel = ProductModel(productsItem!!.productId,productsItem!!.name,productsItem!!.price,productsItem!!.description,productsItem!!.image,""+count)
           viewModel.insertData(this, productModel)
       }else{
           viewModel.updateData(this, ""+count,""+ productsItem!!.productId)
       }
    }

    override fun deleteProduct(productsItem: ProductsItem?, count: Int) {

        for (productListDB in productModel) {
            if (productListDB.productID.equals(productsItem?.productId)) {
                if (count == 0) {
                    viewModel.deleteData(this, productListDB.Id)
                } else {
                    viewModel.updateData(this, ""+count,""+ productsItem!!.productId)
                }
            }
        }

    }
}