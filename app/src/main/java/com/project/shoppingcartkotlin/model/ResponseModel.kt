package com.project.shoppingcartkotlin.model

import com.google.gson.annotations.SerializedName

data class Data(

	@field:SerializedName("products")
	val products: List<ProductsItem?>? = null
)

data class ProductsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("images")
	val images: List<Any?>? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("zoom_thumb")
	val zoomThumb: String? = null,

	@field:SerializedName("special")
	val special: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("product_id")
	val productId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("options")
	val options: List<Any?>? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("href")
	val href: String? = null,

	@field:SerializedName("sku")
	val sku: String? = null
)
