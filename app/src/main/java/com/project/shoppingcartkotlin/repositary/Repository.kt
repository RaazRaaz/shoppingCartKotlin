package com.project.shoppingcartkotlin.repositary

import com.project.shoppingcartkotlin.services.APIService

class Repository constructor(private val retrofitService: APIService) {

    suspend fun getAllProducts() = retrofitService.getAllProducts()




}