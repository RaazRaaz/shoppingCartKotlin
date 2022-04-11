package com.project.shoppingcartkotlin.view

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.view.View.GONE
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

class CartList :AppCompatActivity(), DeleteItem {
    private lateinit var viewModel: ProductViewModel
    private val adapter = CartListAdapter()
    private lateinit var binding:ActivtyProductBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivtyProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val service = APIService.getInstance()
        val repo= Repository(service)
        binding.recyclerView.adapter = adapter
        binding.appBarTitle.text = "CartList"
        binding.cart.visibility = View.GONE
        viewModel = ViewModelProvider(this, MyViewModelFactory(repo)).get(ProductViewModel::class.java)
        getAllProduct()

    }

    private fun getAllProduct() {
        viewModel.getDbProduct(this)!!.observe(this, Observer {
            adapter.setProducts(it, this)


            if (it.isEmpty()) {
                binding.cartEmpty.visibility = View.VISIBLE
            } else {
                binding.cartEmpty.visibility = GONE
            }
        })
    }



    override fun delete(productsItem: ProductModel?, count: Int?) {
        viewModel.deleteData(this, productsItem!!.Id )
        getAllProduct()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, ProductList::class.java))
        finish()
    }




}