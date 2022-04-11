package com.project.shoppingcartkotlin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.shoppingcartkotlin.R
import com.project.shoppingcartkotlin.model.SplashModel
import com.project.shoppingcartkotlin.viewmodel.SplashViewModel

class SplashScreen : AppCompatActivity() {
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        splashViewModel.initSplashScreen()
        val observer = Observer<SplashModel> {
            startActivity(Intent(this, ProductList::class.java))
            finish()
        }
        splashViewModel.liveData.observe(this, observer)

    }

}
